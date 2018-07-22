package com.xyfindables.ui

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.xyfindables.ui.dialogs.XYThrobberDialog
import com.xyfindables.ui.views.XYToolbar
import com.xyfindables.core.XYBase

import io.fabric.sdk.android.Fabric
import java.net.URL

abstract class XYBaseActivity : AppCompatActivity() {

    var _toolbar: XYToolbar? = null

    var throbber: XYThrobberDialog? = null

    val tag : String
        get (){
            val parts = this.localClassName.split('.')
            return parts[parts.lastIndex]
        }

    fun logInfo(message: String) {
        XYBase.logInfo(tag, message)
    }

    fun logExtreme(message: String) {
        XYBase.logExtreme(tag, message)
    }

    fun logError(message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun logException(exception: Exception, debug: Boolean) {
        XYBase.logException(tag, exception, debug)
    }

    fun logException(tag: String, exception: Exception, debug: Boolean) {
        XYBase.logException(tag, exception, debug)
    }

    fun logStatus(tag: String, message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun logInfo(tag: String, message: String) {
        XYBase.logInfo(tag, message)
    }

    fun logExtreme(tag: String, message: String) {
        XYBase.logExtreme(tag, message)
    }

    fun logError(tag: String, message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun logStatus(message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun toolbar(): XYToolbar? {
        if (_toolbar == null) {
            _toolbar = findViewById(R.id.toolbar)
        }
        return _toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XYBase.logStatus(tag, "Activity Created: $tag")
        Fabric.with(this, Answers(), Crashlytics())
        throbber = XYThrobberDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        XYBase.logStatus(tag, "Activity Created: $tag")
        Fabric.with(this, Answers(), Crashlytics())
        throbber = XYThrobberDialog(this)
    }

    override fun onResume() {
        XYBase.logStatus(tag, "Activity Resumed: $tag")
        super.onResume()
        _activityCount++
        XYBase.logInfo(tag, "onResume:" + _activityCount + ":$tag")
    }

    public override fun onStart() {
        XYBase.logStatus(tag, "Activity Started: $tag")
        super.onStart()
    }

    public override fun onStop() {
        XYBase.logStatus(tag, "Activity Stopped: $tag")
        throbber?.dismiss()
        super.onStop()
        _activityCount--
    }

    override fun onDestroy() {
        XYBase.logStatus(tag, "Activity Destroyed: $tag")
        throbber?.dismiss()
        super.onDestroy()
    }

    override fun onPause() {
        XYBase.logStatus(tag, "Activity Paused: $tag")
        throbber?.dismiss()
        hideKeyboard()
        super.onPause()
    }

    fun getRemoteFile(location : String, useCache : Boolean = true) : ByteArray? {
        val url : URL
        if (useCache) {
            url = URL(location);
        } else {
            url = URL(location + "?t=" + Math.random());
        }
        val inputStream = url.openStream()

        val result = inputStream.readBytes()

        inputStream.close()

        return result
    }

    fun getXorValue(array: ByteArray): Byte {
        var cooked = 0
        for (i in array.indices) {
            cooked = (cooked xor array[i].toInt())
        }
        return cooked.toByte()
    }

    fun byteArrayToThreeByteArray(array: ByteArray): Array<Array<ByteArray>> {
        val xor = getXorValue(array)
        val xorArray = byteArrayOf(xor)

        val newArray = ByteArray(array.size + 1)
        System.arraycopy(array, 0, newArray, 0, array.size)
        System.arraycopy(xorArray, 0, newArray, array.size, 1)

        val len = newArray.size
        val patchLen = 128
        val blocks = Math.ceil(len.toDouble() / patchLen.toDouble()).toInt()

        // need to send all chunks of 20, then chunk of modulus remainder
        val firmwareByteArray = emptyArray<Array<ByteArray>>()
        var offset = 0

        for (i in 0 until blocks) {
            var blockSize = patchLen
            if (i + 1 == blocks) {
                blockSize = len % blockSize
            }
            val chunkSize = 20
            var chunkCounter = 0
            firmwareByteArray[i] = emptyArray<ByteArray>()
            var j = 0
            while (j < blockSize) {
                var tempChunkSize = chunkSize
                if (offset + chunkSize > len) {
                    tempChunkSize = len - offset
                } else if (j + chunkSize > blockSize) {
                    tempChunkSize = blockSize % chunkSize
                }
                val chunk = newArray.copyOfRange(offset, offset + tempChunkSize)
                firmwareByteArray[i][chunkCounter] = chunk
                offset += tempChunkSize
                chunkCounter++
                j += 20
            }
        }
        return firmwareByteArray
    }

    fun hexStringToThreeByteArray(string: String): Array<Array<ByteArray>> {
        val result = string.toByteArray()
        val resultLength = result.size
        run {
            var i = 0
            while (i < resultLength) {
                result[i shr 1] = ((if (result[i] < 0x3a) result[i] - 0x30 else result[i] - 0x37) * 16 + if (result[i + 1] < 0x3a) result[i + 1] - 0x30 else result[i + 1] - 0x37).toByte()
                i += 2
            }
        }

        logExtreme(TAG, "testOta-length of result: " + result.size / 2)

        val xor = getXorValue(result)
        val xorArray = byteArrayOf(xor)

        val newResult = ByteArray(result.size + 1)
        System.arraycopy(result, 0, newResult, 0, result.size)
        System.arraycopy(xorArray, 0, newResult, result.size, 1)

        val newResultLength = newResult.size
        // total number of blocks that will be written
        // each block will contain 240 bytes maximum
        val patchLen = 128
        val blocks = Math.ceil(newResultLength.toDouble() / patchLen.toDouble()).toInt()

        // need to send all chunks of 20, then chunk of modulus remainder
        val firmwareByteArray = emptyArray<Array<ByteArray>>()
        var offset = 0

        for (i in 0 until blocks) {
            var blockSize = patchLen
            if (i + 1 == blocks) {
                blockSize = newResultLength % blockSize
            }
            val chunkSize = 20
            var chunkCounter = 0
            firmwareByteArray[i] = emptyArray<ByteArray>()
            var j = 0
            while (j < blockSize) {
                var tempChunkSize = chunkSize
                if (offset + chunkSize > newResultLength) {
                    tempChunkSize = newResultLength - offset
                } else if (j + chunkSize > blockSize) {
                    tempChunkSize = blockSize % chunkSize
                }
                val chunk = result.copyOfRange(offset, offset + tempChunkSize)
                val fwByteArray = firmwareByteArray[i]
                fwByteArray[chunkCounter] = chunk
                offset += tempChunkSize
                chunkCounter++
                j += 20
            }
        }
        return firmwareByteArray
    }

    fun showToast(message: String) {
        XYBase.logInfo(tag, "showProgressBar")
        ui { Toast.makeText(this@XYBaseActivity, message, Toast.LENGTH_LONG).show() }
    }

    fun hideKeyboard() {
        XYBase.logInfo(tag, "hideKeyboard")
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {

        private val TAG = XYBaseActivity::class.java.simpleName

        var _activityCount = 0

        val isForeground: Boolean
            get() = _activityCount > 0
    }
}


package com.lihao.kotlinapp.pages.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.lihao.kotlinapp.R

const val PERMISSION_REQUEST_CODE = 0x133
class ContactPickerActivity: AppCompatActivity() {

    /** 使用新的ActivityResultLauncher代替startActivityForResult。 */
    private val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != RESULT_OK || result.data == null || result.data?.data == null) {
            return@registerForActivityResult
        }
        val contactData: Uri? = result.data?.data
        if (contactData == null) {
            return@registerForActivityResult
        }
        val resolver = contentResolver
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
        val cursor = resolver.query(contactData, projection, null, null, null)
        if (cursor != null && cursor.moveToNext()) {
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val name = cursor.getString(nameIndex)
            val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val phone = cursor.getString(phoneIndex)
            val showView: TextView = findViewById(R.id.show)
            showView.text = name
            val phoneView: TextView = findViewById(R.id.phone)
            phoneView.text = phone
        }
        cursor?.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_picker)
        val btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener { view ->
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent()
                intent.action = Intent.ACTION_PICK
                intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
                resultLauncher.launch(intent)
            }
        }
    }
}
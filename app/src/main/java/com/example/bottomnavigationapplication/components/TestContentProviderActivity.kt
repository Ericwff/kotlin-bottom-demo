package com.example.bottomnavigationapplication.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.bottomnavigationapplication.databinding.ActivityContentProviderBinding
import androidx.core.net.toUri
import android.provider.ContactsContract.CommonDataKinds

class TestContentProviderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        requestPermission()
    }

    private fun initView() {
        binding.apply {
            getContact.setOnClickListener {
                getContacts()
            }
            insertContact.setOnClickListener {
                addContact()
            }
            updateContact.setOnClickListener {
                updateContact()
            }
            deleteContact.setOnClickListener {
                deleteContact()
            }
        }
    }

    private fun requestPermission() {
        val readable = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED
        val writable = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED
        if (!readable || !writable) {
            // 如果未授权
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                100
            )
        } else {
//            getContacts()
        }
    }

    @SuppressLint("Range")
    private fun getContacts() {
//        Toast.makeText(this, "getContacts", Toast.LENGTH_SHORT).show()

        val resolver = contentResolver
        val uri = "content://com.android.contacts/data/phones".toUri()
        val cursor = resolver.query(uri, null, null, null, null)!!

        while (cursor.moveToNext()) {
            val displayName = cursor.getString(cursor.getColumnIndex("display_name"))
            val phoneNumber = cursor.getString(cursor.getColumnIndex("data1"))
            Log.e("ContentProvider", "姓名：${displayName}")
            Log.e("ContentProvider", "号码：${phoneNumber}")
            Log.e("ContentProvider", "==================")
        }
        cursor.close()
    }

    private fun addContact() {
        val resolver = contentResolver
        val values = ContentValues()
        val rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values)!!
        val rawContactId = ContentUris.parseId(rawContactUri)

        // 插入姓名
        values.clear()
        values.put(ContactsContract.Data._ID,rawContactId)
        values.put(ContactsContract.Data.CONTENT_TYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        values.put(CommonDataKinds.StructuredName.GIVEN_NAME,"wangwu")
        resolver.insert(ContactsContract.Data.CONTENT_URI,values)

        // 插入手机号
        values.clear()
        values.put(ContactsContract.Data._ID,rawContactId)
        values.put(ContactsContract.Data.CONTENT_TYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        values.put(CommonDataKinds.Phone.NUMBER,"10000000001")
        values.put(CommonDataKinds.Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
        resolver.insert(ContactsContract.Data.CONTENT_URI,values)

        // 插入邮箱
        values.clear()
        values.put(ContactsContract.Data._ID,rawContactId)
        values.put(ContactsContract.Data.CONTENT_TYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
        values.put(CommonDataKinds.Phone.NUMBER,"10000000001@qq.com")
        values.put(CommonDataKinds.Phone.TYPE, CommonDataKinds.Email.TYPE_WORK)
        resolver.insert(ContactsContract.Data.CONTENT_URI,values)

    }

    private fun deleteContact() {

    }

    private fun updateContact() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (permissions[0] == android.Manifest.permission.READ_CONTACTS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getContacts()
                    Toast.makeText(
                        this,
                        "读取通讯录授权成功",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "读取通讯录的权限被拒绝，程序将无法继续工作",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (permissions[1] == android.Manifest.permission.WRITE_CONTACTS) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "写入通讯录授权成功",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "写入通讯录授权被拒绝，程序将无法继续工作",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
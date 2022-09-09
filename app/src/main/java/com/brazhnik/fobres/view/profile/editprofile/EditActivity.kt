package com.brazhnik.fobres.view.profile.editprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.databinding.ActivityEditBinding
import com.brazhnik.fobres.utilities.displayToast


class EditActivity : AppCompatActivity(), EditView {

    private lateinit var binding: ActivityEditBinding

    @InjectPresenter
    lateinit var editPresenter: EditPresenter

    lateinit var picturePath: String

    @ProvidePresenter
    fun providePresenter(): EditPresenter {
        return EditPresenter()
    }

    override fun onStart() {
        super.onStart()
        editPresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        editPresenter.detachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        editPresenter.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editPresenter = providePresenter()

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handlerButtonClick()

        editPresenter.profileFull.observe(this) {
            editPresenter.updateLocalProfile(it)
            hideLoadingWheel()
            application.displayToast("Profile is update!")
        }
        editPresenter.status.observe(this) {
            hideLoadingWheel()
            showError()
        }
    }

    override fun fillFields(profileFull: ProfileFull) {
        try {
            binding.etFirstName.setText(profileFull.firstName)
            binding.etLastName.setText(profileFull.lastName)
            binding.etStatus.setText(profileFull.status)
            binding.etCity.setText(profileFull.city)
            binding.etCountry.setText(profileFull.country)
            binding.etDescription.setText(profileFull.profileDescription)
            hideLoadingWheel()
        } catch (ex: Exception) {
            applicationContext.displayToast("Something Wrong")
        }
    }

    override fun showError() {
        application.displayToast("Something went wrong!")
    }

    override fun showLoadingWheel() {
        binding.layoutLoadBar.visibility = View.VISIBLE
    }

    override fun hideLoadingWheel() {
        binding.layoutLoadBar.visibility = View.GONE
    }

    private fun handlerButtonClick() {
        binding.btnSaveNewProfile.setOnClickListener {
            if (binding.etFirstName.text != null &&
                binding.etLastName.text != null &&
                binding.etCountry.text != null &&
                binding.etCity.text != null &&
                binding.etDescription.text != null
            ) {
                /**
                 * АЛГОРИТМ
                 * 1) Отправить картинку на сервер
                 * 2) Получить ссылку в ответ
                 * 3) отправить готовый профиль на сервер обнавленный (с сыллкой)
                 * **/
                val tmpProfileFull: ProfileFull = editPresenter.getProfile()
                tmpProfileFull.firstName = binding.etFirstName.text.toString()
                tmpProfileFull.lastName = binding.etLastName.text.toString()
                tmpProfileFull.country = binding.etCountry.text.toString()
                tmpProfileFull.city = binding.etCity.text.toString()
                tmpProfileFull.profileDescription = binding.etDescription.text.toString()


                editPresenter.uploadImage(picturePath, tmpProfileFull.id.toInt())
                editPresenter.responseImageUrl.observe(this) {
                    if(it != null) {
                        tmpProfileFull.profilePicture = it.newUrl  // Update url image
                        editPresenter.updateProfile(tmpProfileFull) // update profile
                    } else {
                        hideLoadingWheel()
                        displayToast("Error profile is not updated")
                    }
                }
            }
        }
        binding.btnEditImage.setOnClickListener {
            openGalleryForImage()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        // TODO FIX deprecated
        startActivityForResult(intent, REQUEST_CODE)
    }

    // TODO FIX deprecated
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            // прикрутить отправку аватарки на сервер
            // ждать ответа от сервера (ссылку на картинку)
            val selectedImage: Uri = data?.data!!
            picturePath = getRealPathFromURI(selectedImage, this)
            binding.imageProfile.setImageURI(data?.data) // handle chosen image
        }
    }

    private fun getRealPathFromURI(contentURI: Uri, context: Activity): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.managedQuery(
            contentURI, projection, null,
            null, null
        ) ?: return null.toString()
        val columnIndex = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        return if (cursor.moveToFirst()) {
            // cursor.close();
            cursor.getString(columnIndex)
        } else null.toString()
        // cursor.close();
    }

    companion object {
        private const val REQUEST_CODE = 100
    }
}
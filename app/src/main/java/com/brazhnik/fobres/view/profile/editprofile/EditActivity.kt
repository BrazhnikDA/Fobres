package com.brazhnik.fobres.view.profile.editprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.databinding.ActivityEditBinding
import com.brazhnik.fobres.utilities.displayToast
import java.lang.Exception

class EditActivity : AppCompatActivity(), EditView {

    private lateinit var binding: ActivityEditBinding

    @InjectPresenter
    lateinit var editPresenter: EditPresenter

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
            // TODO FIX call back fragment or simulate back press and refresh data on screen
            onBackPressed()
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
        } catch (ex: Exception) {
            applicationContext.displayToast("Something Wrong")
        }
    }

    private fun handlerButtonClick() {
        binding.saveNewProfile.setOnClickListener {
            if (binding.etFirstName.text != null &&
                binding.etLastName.text != null &&
                binding.etCountry.text != null &&
                binding.etCity.text != null &&
                binding.etDescription.text != null
            ) {
                val tmpProfileFull: ProfileFull = editPresenter.getProfile()
                tmpProfileFull.firstName = binding.etFirstName.text.toString()
                tmpProfileFull.lastName = binding.etLastName.text.toString()
                tmpProfileFull.country = binding.etCountry.text.toString()
                tmpProfileFull.city = binding.etCity.text.toString()
                tmpProfileFull.profileDescription = binding.etDescription.text.toString()
                editPresenter.updateProfile(tmpProfileFull)
            }
        }
    }
}
package com.brazhnik.fobres.view.donation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.databinding.ActivityDonationBinding
import com.brazhnik.fobres.utilities.displayToast

class DonationActivity : AppCompatActivity(), DonationView {

    private lateinit var binding: ActivityDonationBinding

    @InjectPresenter
    lateinit var presenter: DonationPresenter

    @ProvidePresenter
    fun providePresenter(): DonationPresenter {
        return DonationPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = providePresenter()

        binding.actuallyCourseCoin.setSelectAllOnFocus(true)
        binding.actuallyCourseCoin.text = "Balance: " + SharedData.profileFullCurrent.money

        binding.callPaymentButton.setOnClickListener {

        }

        binding.showADBButton.setOnClickListener {
            presenter.loadADB(this)
        }

        binding.back.setOnClickListener {

        }

        presenter.profileFull.observe(this) {
            SharedData.profileFullCurrent = it
            binding.actuallyCourseCoin.text = "Balance: " + SharedData.profileFullCurrent.money
        }

        presenter.status.observe(this) {
            this.displayToast("Error: $it")
        }
    }
}
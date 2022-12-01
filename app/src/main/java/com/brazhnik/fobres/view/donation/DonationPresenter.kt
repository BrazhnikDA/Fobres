package com.brazhnik.fobres.view.donation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.SharedData.Companion.FULL_SCREEN_ADS_YANDEX
import com.brazhnik.fobres.data.SharedData.Companion.YANDEX_MOBILE_ADS_TAG
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.Payment
import com.brazhnik.fobres.data.model.ProfileFull
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAd
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener

class DonationPresenter: MvpPresenter<DonationView>() {

    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val modelProfileHelper: ModelProfileHelper = ModelProfileHelper(profileFull, status)

    fun updateUserCoins(coins: Int) {
        modelProfileHelper.updateUserCoins(Payment(SharedData.profileFullCurrent.id.toInt(), coins.toDouble()))
    }

    fun loadADB(context: Context) {
        MobileAds.initialize(context) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized");
        }

        val mRewardedAd = RewardedAd(context);
        mRewardedAd.setAdUnitId("R-M-DEMO-rewarded-client-side-rtb");
        val adRequest = AdRequest.Builder().build()

        mRewardedAd.setRewardedAdEventListener(object : RewardedAdEventListener {
            override fun onAdLoaded() {
                mRewardedAd.show();
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Log.d(YANDEX_MOBILE_ADS_TAG, "Не удалось загрузить!!! ${p0.description}");
            }

            override fun onAdShown() {
                Log.d(YANDEX_MOBILE_ADS_TAG, "onAdShown");
            }

            override fun onAdDismissed() {
                Log.d(YANDEX_MOBILE_ADS_TAG, "onAdDismissed");
            }

            override fun onRewarded(p0: Reward) {
                Log.d(YANDEX_MOBILE_ADS_TAG, "Выдать - ${10}");
                Toast.makeText(context, "Награда получена!", Toast.LENGTH_SHORT).show()
                updateUserCoins(10)
            }

            override fun onAdClicked() {
                Log.d(YANDEX_MOBILE_ADS_TAG, "onAdClicked");
            }

            override fun onLeftApplication() {
                Log.d(YANDEX_MOBILE_ADS_TAG, "onLeftApplication");
            }

            override fun onReturnedToApplication() {
                Log.d(YANDEX_MOBILE_ADS_TAG, "onReturnedToApplication");
            }

            override fun onImpression(p0: ImpressionData?) {
                Log.d(YANDEX_MOBILE_ADS_TAG, "Не удалось загрузить!!! ${p0?.rawData}");
            }

        })

        // Загрузка объявления.
        mRewardedAd.loadAd(adRequest);
    }
}
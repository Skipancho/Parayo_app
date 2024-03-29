package com.e.parayo_app.inquiry

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.e.parayo_app.App
import com.e.parayo_app.api.response.InquiryResponse
import com.e.parayo_app.inquiry.registration.InquiryRegistrationActivity
import com.e.parayo_app.inquiry.registration.InquiryRegistrationViewModel
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class ProductInquiryViewModel(app: Application) :
    BaseViewModel(app),
    InquiryPagedAdapter.InquiryLiveDataBuilder,
    InquiryPagedAdapter.InquiryItemClickListener{

    var productId : Long = -1
    val inquiries = buildPagedList()

    override fun createDataSource(): DataSource<Long, InquiryResponse> {
        if (productId == -1L)
            error("productId가 설정되지 않았습니다.",
            IllegalStateException("productId is -1"))

        return InquiryDataSource(productId)
    }

    override fun onClickInquiry(inquiryResponse: InquiryResponse?) {
        //do nothing
    }

    override fun onClickAnswer(inquiryResponse: InquiryResponse?) {
        inquiryResponse?.run { inquiry("ANSWER", id) }
    }

    fun inquiry(type : String, inquiryId : Long? = null){
        val intent = Intent(
            App.instance,
            InquiryRegistrationActivity::class.java
        ).apply {
            putExtra(InquiryRegistrationActivity.PRODUCT_ID, productId)
            putExtra(InquiryRegistrationActivity.INQUIRY_ID, inquiryId)
            putExtra(InquiryRegistrationActivity.INQUIRY_TYPE, type)
        }
        startActivityForResult(intent, REQUEST_CODE_REGISTER_INQUIRY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_REGISTER_INQUIRY -> {
                if (resultCode == InquiryRegistrationViewModel.RESULT_CODE_REGISTER_INQUIRY){
                    finishActivity()
                }
            }
        }
    }

    companion object{
        const val REQUEST_CODE_REGISTER_INQUIRY = 1
    }

}
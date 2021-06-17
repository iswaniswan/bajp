package com.iswan.main.movflix.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.databinding.ItemCompanyBinding
import com.iswan.main.movflix.utils.Utils

class CompaniesAdapter:
    RecyclerView.Adapter<CompaniesAdapter.CompanyViewHolder>() {

    private var companies = listOf<Company>()

    fun setData(companies: List<Company>) {
        this.companies = companies
        this.notifyDataSetChanged()
    }

    inner class CompanyViewHolder(private val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            with (binding) {
                Glide.with(itemView.context)
                    .load(Utils.getImagePath(0, company.logoPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivCompany)
                var companyText = company.name
                if (company.originCountry.isNotEmpty()) companyText += "\n(${company.originCountry})"
                tvCompany.text = companyText
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding: ItemCompanyBinding = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val company = companies[position]
        holder.bind(company)
    }

    override fun getItemCount(): Int = companies.size
}
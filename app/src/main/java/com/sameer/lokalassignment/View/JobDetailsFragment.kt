package com.sameer.lokalassignment.View
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sameer.lokalassignment.Model.JobCategory
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.R
import com.sameer.lokalassignment.Repository.JobSavedRepository
import com.sameer.lokalassignment.ViewModel.JobsSavedViewModel
import com.sameer.lokalassignment.databinding.FragmentJobDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class JobDetailsFragment : Fragment() {
    private lateinit var job: JobEntity
    private lateinit var jobSavedViewModel: JobsSavedViewModel
    private var isBookmarked = false
    private lateinit var binding: FragmentJobDetailsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentJobDetailsBinding.inflate(layoutInflater,container,false)

        @Suppress("DEPRECATION")
        job = arguments?.getParcelable("job")
            ?: throw IllegalArgumentException("Job argument not found")

        job?.let {
            val categoryEnum = JobCategory.fromId(job.categoryId)

            binding.title.text = categoryEnum.toString()
            binding.companyName.text = it.companyName
            binding.location.text = it.location
            binding.minSalary.text = "₹${it.minSalary ?: "-"}"
            binding.maxSalary.text = "₹${it.maxSalary ?: "-"}"
            binding.experience.text = "Experience: ${it.experience}"
            binding.openings.text = "Openings: ${it.openingCount}"
            binding.feesCharged.text = "Fees Charged: ₹${it.feesCharged ?: 0}"
            binding.createdOn.text = "Posted On: ${it.createdOn}"
            binding.otherDetails.text = it.otherDetails

            binding.whatsappButton.setOnClickListener { _ ->
                val whatsappNumber = it.whatsappNumber ?: return@setOnClickListener
                val uri = Uri.parse("https://wa.me/$whatsappNumber")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }

        jobSavedViewModel = ViewModelProvider(requireActivity())[JobsSavedViewModel::class.java]

        lifecycleScope.launch {
            jobSavedViewModel.isBookmarked(job.id).let{ bookmarked ->
                isBookmarked = bookmarked
                updateBookmarkButton(bookmarked)
            }
        }


        binding.bookmark.setOnClickListener {
            if (isBookmarked) {
                jobSavedViewModel.delete(job)
                Toast.makeText(context, "Removed from bookmarks", Toast.LENGTH_SHORT).show()
                isBookmarked = false
                updateBookmarkButton(false)
            } else {
                jobSavedViewModel.insert(job)
                Toast.makeText(context, "Bookmarked", Toast.LENGTH_SHORT).show()
                isBookmarked = true
                updateBookmarkButton(true)
            }
        }

        return binding.root
    }

    private fun updateBookmarkButton(bool: Boolean) {
        val tintColor = if (bool) {
            ContextCompat.getColor(requireContext(), R.color.yellow1)
        } else {
            ContextCompat.getColor(requireContext(), R.color.grey2)
        }
        binding.bookmark.setColorFilter(tintColor)
    }
}
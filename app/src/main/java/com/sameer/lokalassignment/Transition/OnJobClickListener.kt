package com.sameer.lokalassignment.Transition

import com.sameer.lokalassignment.Model.JobEntity

interface OnJobClickListener {
    fun onShowMoreClicked(job: JobEntity)
}

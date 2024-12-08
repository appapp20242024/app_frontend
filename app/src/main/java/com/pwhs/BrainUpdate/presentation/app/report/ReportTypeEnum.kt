package com.pwhs.quickmem.presentation.app.report

import androidx.annotation.StringRes
import com.pwhs.quickmem.R

enum class ReportTypeEnum(
    @StringRes val title: Int,
    @StringRes val questionText: Int,
    @StringRes val options: List<Int>
) {
    STUDY_SET(
        title = R.string.txt_report_this_study_set,
        questionText = R.string.txt_why_report_this_study_set,
        options = listOf(
            R.string.txt_set_inaccurate_information,
            R.string.txt_set_inappropriate,
            R.string.txt_set_cheating,
            R.string.txt_set_ip_violation
        )
    ),
    CLASS(
        title = R.string.txt_report_this_class,
        questionText = R.string.txt_why_report_this_class,
        options = listOf(
            R.string.txt_class_name_misleading,
            R.string.txt_class_inappropriate,
            R.string.txt_class_cheating,
            R.string.txt_class_ip_violation,
            R.string.txt_class_joining_trouble
        )
    ),
    USER_DETAIL(
        title = R.string.txt_report_this_user,
        questionText = R.string.txt_why_report_this_user,
        options = listOf(
            R.string.txt_user_harassment,
            R.string.txt_user_inappropriate_content
        )
    ),
    FOLDER(
        title = R.string.txt_report_this_folder,
        questionText = R.string.txt_why_report_this_folder,
        options = listOf(
            R.string.txt_folder_name_misleading,
            R.string.txt_folder_inappropriate,
            R.string.txt_folder_ip_violation
        )
    )
}
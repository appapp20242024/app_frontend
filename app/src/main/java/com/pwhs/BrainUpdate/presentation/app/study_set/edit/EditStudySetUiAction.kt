package com.pwhs.quickmem.presentation.app.study_set.edit

import com.pwhs.quickmem.domain.model.color.ColorModel
import com.pwhs.quickmem.domain.model.subject.SubjectModel

sealed class EditStudySetUiAction {
    data class TitleChanged(val title: String) : EditStudySetUiAction()
    data class DescriptionChanged(val description: String) : EditStudySetUiAction()
    data class SubjectChanged(val subjectModel: SubjectModel) : EditStudySetUiAction()
    data class ColorChanged(val colorModel: ColorModel) : EditStudySetUiAction()
    data class PublicChanged(val isPublic: Boolean) : EditStudySetUiAction()
    data object SaveClicked : EditStudySetUiAction()
}
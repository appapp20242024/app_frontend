package com.pwhs.quickmem.presentation.app.search_result.all_result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.pwhs.quickmem.R
import com.pwhs.quickmem.domain.model.classes.GetClassByOwnerResponseModel
import com.pwhs.quickmem.domain.model.folder.GetFolderResponseModel
import com.pwhs.quickmem.domain.model.study_set.GetStudySetResponseModel
import com.pwhs.quickmem.domain.model.users.SearchUserResponseModel
import com.pwhs.quickmem.domain.model.users.UserResponseModel
import com.pwhs.quickmem.presentation.app.library.classes.component.ClassItem
import com.pwhs.quickmem.presentation.app.library.folder.component.FolderItem
import com.pwhs.quickmem.presentation.app.library.study_set.component.StudySetItem
import com.pwhs.quickmem.presentation.app.search_result.all_result.component.SectionHeader
import com.pwhs.quickmem.presentation.app.search_result.user.component.SearchUserResultItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAllResultScreen(
    modifier: Modifier = Modifier,
    studySets: LazyPagingItems<GetStudySetResponseModel>? = null,
    onStudySetClick: (GetStudySetResponseModel?) -> Unit = {},
    folders: LazyPagingItems<GetFolderResponseModel>? = null,
    onFolderClick: (GetFolderResponseModel?) -> Unit = {},
    classes: LazyPagingItems<GetClassByOwnerResponseModel>? = null,
    onClassClicked: (GetClassByOwnerResponseModel?) -> Unit = {},
    users: LazyPagingItems<SearchUserResponseModel>? = null,
    onUserItemClicked: (SearchUserResponseModel?) -> Unit = {},
    onSeeAllClickStudySet: () -> Unit = {},
    onSeeAllClickFolder: () -> Unit = {},
    onSeeAllClickClass: () -> Unit = {},
    onSeeAllClickUsers: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        when (studySets?.itemCount == 0 && folders?.itemCount == 0 && classes?.itemCount == 0 && users?.itemCount == 0) {
            true -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 40.dp)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_flashcards),
                        contentDescription = stringResource(R.string.txt_no_results_found),
                    )
                    Text(
                        text = stringResource(R.string.txt_no_results_found),
                        style = typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }

            false -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                ) {
                    if (studySets?.itemCount != 0) {
                        item {
                            SectionHeader(
                                title = stringResource(R.string.txt_study_sets),
                                onSeeAllClick = onSeeAllClickStudySet
                            )
                        }
                        items(studySets?.itemCount?.coerceAtMost(4) ?: 0) {
                            val studySet = studySets?.get(it)
                            StudySetItem(
                                studySet = studySet,
                                onStudySetClick = { onStudySetClick(studySet) }
                            )
                        }
                    }

                    if (folders?.itemCount != 0) {
                        item {
                            SectionHeader(
                                title = stringResource(R.string.txt_folders),
                                onSeeAllClick = onSeeAllClickFolder
                            )
                        }
                        items(folders?.itemCount?.coerceAtMost(4) ?: 0) {
                            val folder = folders?.get(it)
                            FolderItem(
                                title = folder?.title ?: "",
                                numOfStudySets = folder?.studySetCount ?: 0,
                                onClick = { onFolderClick(folder) },
                                userResponseModel = folder?.owner ?: UserResponseModel(),
                                folder = folder
                            )
                        }
                    }

                    if (classes?.itemCount != 0) {
                        item {
                            SectionHeader(
                                title = stringResource(R.string.txt_classes),
                                onSeeAllClick = onSeeAllClickClass
                            )
                        }
                        items(classes?.itemCount?.coerceAtMost(4) ?: 0) {
                            val classItem = classes?.get(it)
                            ClassItem(
                                classItem = classItem,
                                onClick = { onClassClicked(classItem) }
                            )
                        }
                    }

                    if (users?.itemCount != 0) {
                        item {
                            SectionHeader(
                                title = stringResource(R.string.txt_users),
                                onSeeAllClick = onSeeAllClickUsers
                            )
                        }
                        items(users?.itemCount?.coerceAtMost(4) ?: 0) {
                            val user = users?.get(it)
                            SearchUserResultItem(
                                searchMemberModel = user,
                                onClicked = { onUserItemClicked(user) }
                            )
                        }
                    }
                }
            }
        }
    }
}
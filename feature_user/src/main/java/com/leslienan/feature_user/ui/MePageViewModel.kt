package com.leslienan.feature_user.ui

import androidx.lifecycle.ViewModel
import com.leslienan.data_user.UserManager
import com.leslienan.data_user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@HiltViewModel
class MePageViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userName = MutableStateFlow(UserManager.username)
    val userName = _userName.asStateFlow()


    init {

    }

}
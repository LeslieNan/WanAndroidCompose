package com.leslienan.data_user

import android.content.Context
import com.leslienan.core_network.RetrofitModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}

@Module
@InstallIn(ViewModelComponent::class)
object UserRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserRepository(@ApplicationContext context: Context, userApi: UserApi) = UserRepository(context, userApi)
}
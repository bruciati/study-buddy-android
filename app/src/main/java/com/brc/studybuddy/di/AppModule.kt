package com.brc.studybuddy.di

import com.brc.studybuddy.data.repository.mock.AccessTokenRepositoryMock
import com.brc.studybuddy.data.repository.mock.GroupRepositoryMock
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.GroupRepository
import com.brc.studybuddy.domain.use_case.groups.CreateGroup
import com.brc.studybuddy.domain.use_case.groups.GetGroups
import com.brc.studybuddy.domain.use_case.groups.GroupUseCases
import com.brc.studybuddy.domain.use_case.login.FacebookLogin
import com.brc.studybuddy.domain.use_case.login.LoginUseCases
import com.brc.studybuddy.domain.use_case.login.NormalLogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    * Provide a fake datasource implementation
    */
    @Provides
    @Singleton
    fun injectGroupRepository(): GroupRepository = GroupRepositoryMock()

    /*
    * Provide a fake datasource implementation
    */
    @Provides
    @Singleton
    fun injectAccessTokenRepository(): AccessTokenRepository = AccessTokenRepositoryMock()

//    /*
//     * Wire use cases and their repository dependency
//     */
//    @Provides
//    @Singleton
//    fun injectNavigator(): Navigator = Navigator()

    /*
     * Wire use cases and their repository dependency
     */
    @Provides
    @Singleton
    fun injectGroupUseCases(repository: GroupRepository): GroupUseCases = GroupUseCases(
        getGroups = GetGroups(repository),
        createGroup = CreateGroup(repository),
    )


    /*
     * Wire use cases and their repository dependency
     */
    @Provides
    @Singleton
    fun injectLoginUseCases(): LoginUseCases = LoginUseCases(
        facebookLogin = FacebookLogin(),
        normalLogin = NormalLogin(),
    )

}
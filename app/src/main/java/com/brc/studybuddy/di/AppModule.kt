package com.brc.studybuddy.di

import com.brc.studybuddy.data.repository.mock.AccessTokenRepositoryMock
import com.brc.studybuddy.data.repository.mock.GroupRepositoryMock
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.GroupRepository
import com.brc.studybuddy.domain.use_case.groups.CreateGroup
import com.brc.studybuddy.domain.use_case.groups.GetGroups
import com.brc.studybuddy.domain.use_case.groups.GroupUseCases
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
     * Wire use cases and their repository dependency
     */
    @Provides
    @Singleton
    fun injectGroupUseCases(repository: GroupRepository): GroupUseCases = GroupUseCases(
        getGroups = GetGroups(repository),
        createGroup = CreateGroup(repository),
    )

    /*
    * Provide a fake datasource implementation
    */
    @Provides
    @Singleton
    fun injectAccessTokenRepository(): AccessTokenRepository = AccessTokenRepositoryMock()

}
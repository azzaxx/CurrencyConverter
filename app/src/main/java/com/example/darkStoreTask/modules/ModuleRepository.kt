package com.example.darkStoreTask.modules

import android.content.Context
import com.example.darkStoreTask.network.WebService
import com.example.darkStoreTask.repository.NetworkRepository
import com.example.darkStoreTask.repository.NetworkRepositoryImp
import com.example.darkStoreTask.repository.StorageRepository
import com.example.darkStoreTask.repository.StorageRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModuleRepository {
    @Provides
    fun provideNetworkRepository(webService: WebService): NetworkRepository {
        return NetworkRepositoryImp(webService)
    }

    @Provides
    fun provideStorageRepository(@ApplicationContext context: Context): StorageRepository {
        return StorageRepositoryImp(context)
    }
}
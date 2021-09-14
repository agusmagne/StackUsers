package com.example.stackusers.dagger

import com.example.stackusers.model.service.StackService
import com.example.stackusers.network.StackApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideStackService(stackApi: StackApi): StackService {
        return StackService(stackApi)
    }

}
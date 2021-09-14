package com.example.stackusers.dagger

import com.example.stackusers.network.Config
import com.example.stackusers.network.StackApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.BASE_URL).build()
    }

    @Singleton
    @Provides
    fun provideStackApi(retrofit: Retrofit): StackApi {
        return retrofit.create(StackApi::class.java)
    }
}
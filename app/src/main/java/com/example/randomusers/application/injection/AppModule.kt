package com.example.randomusers.application.injection

import android.content.Context
import androidx.appcompat.view.menu.MenuPresenter
import androidx.room.Room
import com.example.randomusers.application.AppConstants.BASE_URL
import com.example.randomusers.application.AppConstants.DATABASE_NAME
import com.example.randomusers.data.local.LocalDataSource
import com.example.randomusers.data.local.UserRoomDatabase
import com.example.randomusers.data.remote.ApiService
import com.example.randomusers.data.remote.NetworkDataSource
import com.example.randomusers.domain.DefaultUserRepository
import com.example.randomusers.domain.UserRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        UserRoomDatabase::class.java,
        DATABASE_NAME)
        .fallbackToDestructiveMigration(false)
        .build()

    @Provides
    fun provideCocktailDao(db: UserRoomDatabase) = db.userDao()


    private val json = Json { isLenient = true }

    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    fun provideWebService(retrofit:Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideNetworkDataSource(apiService: ApiService) : NetworkDataSource = NetworkDataSource(apiService)

    @Provides
    fun provideUserService(networkDataSource: NetworkDataSource, localDataSource: LocalDataSource) : UserRepository =
        DefaultUserRepository(networkDataSource,localDataSource)
}


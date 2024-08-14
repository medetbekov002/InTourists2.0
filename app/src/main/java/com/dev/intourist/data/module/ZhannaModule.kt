package com.dev.intourist.data.module

import ContactRepository
import com.dev.intourist.data.BuildConfig
import com.dev.intourist.data.remote.service.ApiService
//import com.dev.intourist.data.repository.ContactRepository
import com.dev.intourist.data.repository.ToursRepository
import com.dev.intourist.domain.repository.ContactsRepositoryInt
import com.dev.intourist.domain.repository.TourRepositoryInt
import com.dev.intourist.domain.usecase.ContactsUseCase
import com.dev.intourist.domain.usecase.TourUseCase
import com.dev.intourist.ui.screen.auth.AuthViewModel
import com.dev.intourist.ui.screen.home.HomeViewModel
import com.dev.intourist.ui.screen.tour_details.TourDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        provideAppService(get())
    }
    factory {
        provideRetrofitTour(get())
    }
    single {
        provideLogginInterseptor()
    }
    single {
        provideOkHttpClientTours(get())
    }

}

val repositoryModule = module {
    /*single { CoroutineScope(Dispatchers.IO + SupervisorJob()) }*/

    single<TourRepositoryInt> { ToursRepository(get()) }
    
    single<ContactsRepositoryInt> { ContactRepository(get()) }

    single<ToursRepository> { ToursRepository(get()) }


}

val useCasesModule = module {
    single { TourUseCase(get()) }
    single { ContactsUseCase(get()) }
}

val viewModelModule = module {
    viewModel {
        TourDetailsViewModel(get(), get())
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModelOf(::AuthViewModel)
}

val zhannaModule = listOf(networkModule, repositoryModule, useCasesModule, viewModelModule)

fun provideRetrofitTour(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL2)
        .client(okHttpClient)
        .build()
}


fun provideOkHttpClientTours(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideLogginInterseptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideAppService(
    retrofit: Retrofit
): ApiService {
    return retrofit.create(ApiService::class.java)
}




package com.example.composeriky.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.example.composeriky.data.room.RickyDao
import com.example.composeriky.data.room.RickyDataBase
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
//Poner el alcance:SingletonComponent::class es para toda la app
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Singleton // pq solo un objeto
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            //Primera parte de la url acabado en /
            .baseUrl("https://rickandmortyapi.com/")
            //para convertir el gson en la clase
            .addConverterFactory(GsonConverterFactory.create())
            //para construir el retrofit
            .build()
    }


    //Flow:Room:


    @Module
    @InstallIn(SingletonComponent::class)
    class DatabaseModule {
        //Para las acciones, el DAO
        @Provides
        fun providesTask(todoDatabase: RickyDataBase):RickyDao{
            return todoDatabase.taskDao()
        }

        //Para la Database, la lista de datos:
        @Provides
        @Singleton
        fun provideTodoDatabase(@ApplicationContext appContext: Context):RickyDataBase{
            return Room.databaseBuilder(appContext, RickyDataBase::class.java, "RickyDataBase").build()
        }
    }

}

package com.arash.altafi.sampleflow1.data

import android.content.Context
import com.arash.altafi.sampleflow1.model.NetworkResult
import com.arash.altafi.sampleflow1.model.Pokemon
import com.arash.altafi.sampleflow1.model.PokemonResponse
import com.arash.altafi.sampleflow1.data.remote.BaseApiResponse
import com.arash.altafi.sampleflow1.data.remote.RemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @ApplicationContext context: Context
) : BaseApiResponse(context) {

    suspend fun getAllPokemon(): Flow<NetworkResult<PokemonResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getAllPokemon() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPokemonDetails(id: String): Flow<NetworkResult<Pokemon>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getPokemonDetails(id) })
        }.flowOn(Dispatchers.IO)
    }

}
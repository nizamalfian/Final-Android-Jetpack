package app.interactive.academy.data

import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.remote.RemoteRepository

/**
 * Created by L
 *
 * on 7/9/2019
 */
class FakeAcademyRepository(private val localRepository: LocalRepository,private val remoteRepository: RemoteRepository){

}
package com.okit.ustBazaar.repositories

import com.okit.ustBazaar.data.local.RoomDao
import com.okit.ustBazaar.room_models.User
import com.okit.ustBazaar.sealed.DataResponse
import com.okit.ustBazaar.sealed.Error
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dao: RoomDao,
) {
    /** Fake login operation with email and password */
    suspend fun signInUser(email: String, password: String): DataResponse<User> {
        return dao.fakeSignIn(email = email, password = password)?.let {
            DataResponse.Success(data = it)
        } ?: DataResponse.Error(error = Error.Empty)
    }

    /** Save the user to local storage */
    suspend fun saveUserLocally(user: User) {
        dao.saveUser(user = user)
    }

    /** Get current logged user from local */
    suspend fun getLoggedUser(userId: Int) = dao.getLoggedUser(userId = userId)

    /** Get the available payment providers for current user */
    suspend fun getUserPaymentProviders() =
        dao.getUserPaymentProviders()

    /** Get the available locations for current user */
    suspend fun getUserLocations() = dao.getUserLocations()
}
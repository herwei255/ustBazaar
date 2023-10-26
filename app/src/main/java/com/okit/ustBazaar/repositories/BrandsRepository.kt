package com.okit.ustBazaar.repositories

import com.okit.ustBazaar.data.local.RoomDao
import com.okit.ustBazaar.room_models.Advertisement
import com.okit.ustBazaar.room_models.Category
import com.okit.ustBazaar.sealed.DataResponse
import com.okit.ustBazaar.sealed.Error
import com.okit.ustBazaar.utils.getStructuredCategories
import javax.inject.Inject

class BrandsRepository @Inject constructor(
    private val dao: RoomDao,
) {
    suspend fun getBrandsAdvertisements(): DataResponse<List<Advertisement>> {
        /** First we should check the local storage */
        dao.getAdvertisements().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }

    suspend fun getBrandsWithProducts(): DataResponse<List<Category>> {
        /** First we should check the local storage */
        dao.getCategoriesWithProducts().getStructuredCategories().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }
}


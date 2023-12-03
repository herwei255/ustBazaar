package com.okit.ustBazaar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.okit.ustBazaar.R
import com.okit.ustBazaar.room_models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [
        Advertisement::class,
        Category::class,
        Review::class,
        User::class,
        PaymentProvider::class,
        UserPaymentProvider::class,
        Product::class,
        BookmarkItem::class,
        Location::class,
        CartItem::class,
        Order::class,
        OrderItem::class,
        OrderPayment::class,
        Notification::class,
        ProductColor::class,
        ProductSize::class,
        Condition::class
    ],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {

    /** A function that used to retrieve Room's related dao instance */
    abstract fun getDao(): RoomDao

    class PopulateDataClass @Inject constructor(
        private val client: Provider<RoomDb>,
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {
        private val description =
            "This is the description text that is supposed to be long enough to show how the UI looks, so it's not a real text.\n"
        private val categories = listOf(
            Category(id = 1, name = "Apparels", icon = R.drawable.ic_apparel),
            Category(id = 2, name = "Dorm", icon = R.drawable.ic_dorm),
            Category(id = 3, name = "Free Items", icon = R.drawable.ic_free_items),
            Category(id = 4, name = "Textbooks", icon = R.drawable.ic_book),
        )
        private val apparelProducts = listOf(
            Product(
                id = 1,
                name = "Black Shirt",
                image = R.drawable.apparel_1,
                price = 149.0,
                description = description,
                categoryId = 1,
                basicColorName = "dark-green",
            ),
            Product(
                id = 2,
                name = "Red Shirt",
                image = R.drawable.apparel_2,
                price = 159.0,
                description = description,
                categoryId = 1,
                basicColorName = "gold",
            ),
            Product(
                id = 3,
                name = "Maroon Shirt",
                image = R.drawable.apparel_3,
                price = 120.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 4,
                name = "Old Uncle Shirt",
                image = R.drawable.apparel_4,
                price = 421.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 5,
                name = "Pants",
                image = R.drawable.apparel_5,
                price = 15.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 6,
                name = "Formal Pants",
                image = R.drawable.apparel_6,
                price = 152.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 7,
                name = "Sweater",
                image = R.drawable.apparel_7,
                price = 122.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 8,
                name = "Green Sweater",
                image = R.drawable.apparel_8,
                price = 152.0,
                description = description,
                categoryId = 1,
                basicColorName = "black",
            ),
        )
        private val otherProducts = listOf(
            Product(
                id = 10,
                name = "Hanger",
                image = R.drawable.dorm_1,
                price = 10.0,
                description = description,
                categoryId = 2,
                basicColorName = "green",
            ),
            Product(
                id = 11,
                name = "Hair Dryer",
                image = R.drawable.dorm_2,
                price = 159.0,
                description = description,
                categoryId = 2,
                basicColorName = "gray",
            ),
            Product(
                id = 12,
                name = "Tissues",
                image = R.drawable.dorm_3,
                price = 5.0,
                description = description,
                categoryId = 2,
                basicColorName = "gray",
            ),
            Product(
                id = 13,
                name = "Expensive Textbook",
                image = R.drawable.textbook_1,
                price = 150.0,
                description = description,
                categoryId = 4,
                basicColorName = "gray",
            ),
            Product(
                id = 14,
                name = "Free Textbook",
                image = R.drawable.textbook_2,
                price = 0.0,
                description = description,
                categoryId = 3,
                basicColorName = "gray",
            ),

        )
        private val paymentProviders = listOf(
            PaymentProvider(
                id = "apple",
                title = R.string.apple_pay,
                icon = R.drawable.ic_apple,
            ),
            PaymentProvider(
                id = "master",
                title = R.string.master_card,
                icon = R.drawable.ic_master_card,
            ),
            PaymentProvider(
                id = "visa",
                title = R.string.visa,
                icon = R.drawable.ic_visa,
            ),
        )
        private val userPaymentAccounts = listOf(
            UserPaymentProvider(
                providerId = "apple",
                cardNumber = "8402-5739-2039-5784"
            ),
            UserPaymentProvider(
                providerId = "master",
                cardNumber = "3323-8202-4748-2009"
            ),
            UserPaymentProvider(
                providerId = "visa",
                cardNumber = "7483-02836-4839-2833"
            ),
        )
        private val userLocation = Location(
            address = "HKUST",
            city = "Hong Kong",
            country = "China",
        )

        init {
            scope.launch {
                populateDatabase(dao = client.get().getDao(), scope = scope)
            }
        }

        private suspend fun populateDatabase(dao: RoomDao, scope: CoroutineScope) {
            /** Save users */
            scope.launch {
                dao.saveUser(
                    User(
                        userId = 1,
                        name = "Okit Testing User",
                        profile = R.drawable.empty_user,
                        phone = "+852 1234 5678",
                        email = "okit@gmail.com",
                        password = "12344321",
                        token = "ds2f434ls2ks2lsj2ls",
                    )
                )
            }
            /** insert categories */
            scope.launch {
                categories.forEach {
                    dao.insertCategory(it)
                }
            }
            /** Insert products */
            scope.launch {
                apparelProducts.plus(otherProducts).forEach {
                    /** Insert the product itself */
                    dao.insertProduct(product = it)
                }
            }
            /** Insert payment providers */
            scope.launch {
                paymentProviders.forEach {
                    dao.savePaymentProvider(paymentProvider = it)
                }
            }
            /** Insert user's payment providers */
            scope.launch {
                userPaymentAccounts.forEach {
                    dao.saveUserPaymentProvider(it)
                }
            }
            /** Insert user's location */
            scope.launch {
                dao.saveLocation(location = userLocation)
            }
        }
    }

}

class Converters {
    @TypeConverter
    fun fromConditionStatus(conditionStatus: ConditionStatus): String {
        return conditionStatus.name
    }

    @TypeConverter
    fun toConditionStatus(conditionStatus: String): ConditionStatus {
        return ConditionStatus.valueOf(conditionStatus)
    }
}
package com.kvn.checkincmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
/*import com.kvn.checkincmp.ui.details.TravelItemDetailsScreen
import com.kvn.checkincmp.ui.listing.HomeListingScreen*/
import com.kvn.checkincmp.ui.signin.LoginScreen
import com.kvn.checkincmp.ui.signup.SignUpScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun TrevnorNavRoot(userToken: String?) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())
                    subclass(NavRoutes.SignUp::class, NavRoutes.SignUp.serializer())
                    subclass(NavRoutes.Listing::class, NavRoutes.Listing.serializer())
                }
            }
        },
        if(userToken!=null) NavRoutes.Listing else NavRoutes.Login
    )

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = { key ->

            when (key) {
                is NavRoutes.Login -> NavEntry(key) {
                    LoginScreen(backStack = backStack)
                }

                is NavRoutes.SignUp -> NavEntry(key) {
                    SignUpScreen(backStack = backStack)
                }

                is NavRoutes.Listing -> NavEntry(key) {
                  //  HomeListingScreen(backStack = backStack)
                }

                is NavRoutes.ListingDetails -> NavEntry(key) {
                  //  TravelItemDetailsScreen(backStack = backStack, itemId = key.id)
                }

                else -> error("Unknown NavRoute: $key")
            }
        }
    )
}
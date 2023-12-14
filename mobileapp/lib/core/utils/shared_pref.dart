import 'package:mobileapp/app/data/models/cart.dart';
import 'package:mobileapp/app/data/models/user.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter/foundation.dart';

class TbSharedPref {
  static User? _userLogin;
  static Cart? _cart;
  static bool? _guestLogin;

  static void setGuestLogin() {
    _guestLogin = true;
  }

  static bool? getGuestLogin() {
    return _guestLogin;
  }

  static void setUserLogin(User? user, Cart? cartUser) {
    _userLogin = user;
    _cart = cartUser;
  }

  static void setUser(User? user) {
    _userLogin = user;
  }

  static User? getUserLogin() {
    return _userLogin;
  }

  static Cart? getCartUser() {
    return _cart;
  }

  static Future<String> getAccessToken() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('access_token') ?? '';
  }

  static Future<void> setAccessToken(String accessToken) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString('access_token', accessToken);
  }

  static Future<void> removeAccessToken() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.remove('access_token');
  }

// Save a value in shared_preferences
  static Future<void> saveValueToSharedPreferences(
      String key, dynamic value) async {
    try {
      final SharedPreferences prefs = await SharedPreferences.getInstance();
      if (value is String) {
        prefs.setString(key, value);
      } else if (value is int) {
        prefs.setInt(key, value);
      } else if (value is double) {
        prefs.setDouble(key, value);
      } else if (value is bool) {
        prefs.setBool(key, value);
      } else if (value is List<String>) {
        prefs.setStringList(key, value);
      } else {
        // Handle unsupported data types if needed
        throw Exception('Unsupported data type for shared_preferences.');
      }
    } catch (e) {
      // Handle exceptions if necessary
      if (kDebugMode) {
        print('Error saving value to shared_preferences: $e');
      }
    }
  }

// Fetch a value from shared_preferences
  static Future<dynamic> fetchValueFromSharedPreferences(String key) async {
    try {
      final SharedPreferences prefs = await SharedPreferences.getInstance();
      return prefs.get(key);
    } catch (e) {
      // Handle exceptions if necessary
      if (kDebugMode) {
        print('Error fetching value from shared_preferences: $e');
      }
      return null;
    }
  }
}

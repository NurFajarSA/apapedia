import 'dart:convert';
import 'dart:io';

import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'package:mobileapp/app/data/models/user.dart';
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/app/data/services/order_service.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class AuthService extends GetxService {
  Future<bool> signIn(
      {required String usernameEmail, required String password}) async {
    var url = Uri.parse('${Api.baseUrlUser}/auth/login');
    var headers = <String, String>{
      'Content-Type': 'application/json',
    };
    var body = <String, String>{
      'usernameEmail': usernameEmail,
      'password': password,
    };

    var response =
        await http.post(url, headers: headers, body: jsonEncode(body));

    if (response.statusCode == HttpStatus.ok) {
      var jwt = jsonDecode(response.body)['content']['token'];
      await TbSharedPref.setAccessToken(jwt);

      Map<String, dynamic> decodedToken = JwtDecoder.decode(jwt);
      var headers = <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $jwt'
      };
      var responseUser = await http.get(
          Uri.parse('${Api.baseUrlUser}/user/${decodedToken['sub']}'),
          headers: headers);
      var user = User.fromJson(jsonDecode(responseUser.body));
      // var cart = await Get.find<OrderService>().getCartByCustomerId(user.id);
      TbSharedPref.setUserLogin(user, null);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<http.Response> register(
      {required email,
      required name,
      required username,
      required password,
      required address}) async {
    var url = Uri.parse('${Api.baseUrlUser}/auth/signup/customer');
    var headers = <String, String>{
      'Content-Type': 'application/json',
    };
    var body = <String, String>{
      'name': name,
      'username': username,
      'password': password,
      'email': email,
      'address': address,
    };

    var response =
        await http.post(url, headers: headers, body: jsonEncode(body));

    return response;
  }

  Future<void> logout() async {
    TbSharedPref.removeAccessToken();
    TbSharedPref.setUserLogin(null, null);
  }
}

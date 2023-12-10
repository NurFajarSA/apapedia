import 'dart:convert';
import 'dart:io';

import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'package:mobileapp/app/data/models/user.dart';
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class AuthService extends GetxService {
  Future<bool> signIn(
      {required String usernameEmail, required String password}) async {
    var url = Uri.parse('${Api.baseUrl}/auth/login');
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

      var user = User.fromJson(decodedToken);
      TbSharedPref.setUserLogin(user);
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
    var url = Uri.parse('${Api.baseUrl}/auth/signup/customer');
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
    TbSharedPref.setUserLogin(null);
  }
}

import 'dart:convert';

import 'package:mobileapp/app/data/models/user.dart';
import 'package:http/http.dart' as http;
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';

class UserService {
  Future<bool> updateProfile(User updatedUser) async {
    var token = await TbSharedPref.getAccessToken();
    var body = <String, String>{
      'id': updatedUser.id,
      'name': updatedUser.name,
      'address': updatedUser.address,
    };
    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrl}/user/update');

    var response =
        await http.post(url, headers: headers, body: jsonEncode(body));

    if (response.statusCode == 200) {
      TbSharedPref.setUserLogin(updatedUser);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<bool> topUpBalance(double amount) async {
    var token = await TbSharedPref.getAccessToken();
    var body = <String, String>{
      'amount': amount.toString(),
    };

    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrl}/user/topup');

    var response =
        await http.post(url, headers: headers, body: jsonEncode(body));

    if (response.statusCode == 200) {
      var user = TbSharedPref.getUserLogin();
      user!.balance = (user.balance + amount) as int;
      TbSharedPref.setUserLogin(user);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }
}

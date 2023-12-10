import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';

class UserService {
  Future<bool> updateProfile(String name, String address) async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();

    var body = <String, String>{
      'id': user!.id.toString(),
      'name': name,
      'address': address,
    };
    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrl}/user/update');

    var response =
        await http.post(url, headers: headers, body: jsonEncode(body));

    if (response.statusCode == 200) {
      user.name = name;
      user.address = address;
      TbSharedPref.setUserLogin(user);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<bool> topUpBalance(int amount) async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();
    var idUser = user!.id;

    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url =
        Uri.parse('${Api.baseUrl}/user/update-balance/$idUser?amount=$amount');

    var response = await http.post(url, headers: headers);

    if (response.statusCode == 200) {
      var user = TbSharedPref.getUserLogin();
      user!.balance = user.balance + amount;
      TbSharedPref.setUserLogin(user);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<bool> deleteAccount() async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();
    var idUser = user!.id;

    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrl}/user/delete/$idUser');

    var response = await http.delete(url, headers: headers);

    if (response.statusCode == 200) {
      TbSharedPref.removeAccessToken();
      TbSharedPref.setUserLogin(null);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }
}

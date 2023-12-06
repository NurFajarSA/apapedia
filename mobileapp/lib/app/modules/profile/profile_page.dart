import 'package:flutter/material.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
        attr: ScaffoldAttribute(),
        appBar: AppBar(
          title: const Text('Profile'),
        ),
        body: SingleChildScrollView(
            child: Container(
          width: MediaQuery.of(context).size.width,
          margin: const EdgeInsets.all(16),
          child: const Column(
            children: [Text("Profile Page")],
          ),
        )));
  }
}

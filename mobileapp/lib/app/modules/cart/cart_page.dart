import 'package:flutter/material.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CartPage extends StatelessWidget {
  const CartPage({super.key});

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
      attr: ScaffoldAttribute(),
      appBar: AppBar(
        title: const Text('Cart'),
      ),
      body: SingleChildScrollView(
        child: Container(
          width: MediaQuery.of(context).size.width,
          margin: const EdgeInsets.all(16),
          child: const Column(
            children: [Text("Cart Page")],
          ),
        ),
      ),
    );
  }
}

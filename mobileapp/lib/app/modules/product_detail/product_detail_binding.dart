import 'package:get/get.dart';
import 'package:mobileapp/app/modules/product_detail/product_detail_controller.dart';

class ProductDetailBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(ProductDetailController());
  }
}

# aggregate-project

maven { url 'https://jitpack.io' }

implementation 'com.github.savesherry:aggregate-project:1.0.3'


### 等待loading

##### 展示
            ProgressHelper.show(this);
##### 取消
            ProgressHelper.hide();

### 防点击按钮多次点击

            button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
            });

### 底部弹框

            dialog = new SingleChoiceDialog(this, list, "标题位置");
            dialog.setListener(position -> {
                Toast.makeText(MainActivity.this, "点击事件" + position, Toast.LENGTH_SHORT).show();
            });
            dialog.show(parentLayout);

### 请求权限

            RequestPermissions.obtainPermission(this, ConstantPermission.ACCESS_FINE_LOCATION, new PermissionCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "同意", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "不同意", Toast.LENGTH_SHORT).show();
            }
            });
            
### 图片缩略图

            GlideEngine.instantiation().loadImageSize(this, imageView, CustomConstant.imagePath,
                (width, height) -> {
                    Methods.imageViewScale(imageView, width, height);
                });
                
### 缩略图放大功能

            Intent intent = new Intent(ThumbnailActivity.this, EnlargeActivity.class);
            intent.putExtra("imagePath", CustomConstant.imagePath);
            // ready for transition options
            ActivityTransitionOptions options =
                    ActivityTransitionOptions.makeTransitionOptions(
                            ThumbnailActivity.this,
                            findViewById(R.id.imageView));
            // start transition
            ActivityTransition.startActivity(intent, options);

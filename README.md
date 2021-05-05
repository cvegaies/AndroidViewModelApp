# AndroidViewModel

## Ejemplo en el que se utiliza el AndroidViewModel.

Enlaces relacionados:

[https://developer.android.com/topic/libraries/architecture/viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel)

[https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies](https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies)

La clase AndroidViewModel se usa para almacenar y administrar datos relacionados con la interfaz de usuario de manera optimizada para los ciclos de vida.
Esta clase se utiliza cuando se necesita el contexto para implementar operaciones en el repositorio.

> ModeloVistaAndroid viewModel = new ViewModelProvider(this,  
  ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModeloVistaAndroid.class);

![Imagen que muestra el ciclo de vida de un ViewModel](https://developer.android.com/images/topic/libraries/architecture/viewmodel-lifecycle.png)

Los objetos AndroidViewModel pueden contener LifecycleObservers, como objetos LiveData.

> viewModel.getContactos().observe(this, new Observer&lt;List&lt;String&gt;&gt;() {  
 @Override  
 public void onChanged(List&lt;String&gt; data) {  
  //  
 }  
});
 
Se implementa una hebra utilizando la clase **ExecutorService**:

> ExecutorService threadExecutor = Executors.newFixedThreadPool(4);

> threadExecutor.execute(new Runnable() {  
 @Override  
 public void run() {  
   //...  
  }  
 };  

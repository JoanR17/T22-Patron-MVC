package Backend.Ej2;

import Backend.Ej2.controller.*;
import Backend.Ej2.model.service.*;
import Backend.Ej2.view.*;

public class App 
{
	ClienteServ miclienteServ;
	VideosServ mivideosServ;
	VentanaPrincipal miVentanaPrincipal;
	VentanaBuscarCliente miVentanaBuscarCliente;
	VentanaBuscarVideos miVentanaBuscarVideos;
	VentanaRegistroCliente miVentanaRegistroCliente;
	VentanaRegistroVideos miVentanaRegistroVideos;
	ClienteController clienteController;
	VideosController videosController;

	public static void main(String[] args) 
	{
		App miPrincipal=new App();
		miPrincipal.iniciar();
	}

	/**
	 * Permite instanciar todas las clases con las que trabaja
	 * el sistema
	 */
	private void iniciar() {
		/*Se instancian las clases*/
		miVentanaPrincipal = new VentanaPrincipal();
		miVentanaRegistroCliente = new VentanaRegistroCliente();
		miVentanaRegistroVideos = new VentanaRegistroVideos();
		miVentanaBuscarCliente = new VentanaBuscarCliente();
		miVentanaBuscarVideos = new VentanaBuscarVideos();
		miclienteServ = new ClienteServ();
		mivideosServ = new VideosServ();
		clienteController = new ClienteController();
		videosController = new VideosController();
		
		/*Se establecen las relaciones entre clases*/
		miVentanaPrincipal.setCoordinadores(clienteController, videosController);
		miVentanaRegistroCliente.setCoordinador(clienteController);
		miVentanaBuscarCliente.setCoordinador(clienteController);
		miclienteServ.setclienteController(clienteController);
		miVentanaRegistroVideos.setCoordinador(videosController);
		miVentanaBuscarVideos.setCoordinador(videosController);
		mivideosServ.setvideosController(videosController);
		
		/*Se establecen relaciones con la clase coordinador*/
		clienteController.setMiVentanaPrincipal(miVentanaPrincipal);
		clienteController.setMiVentanaRegistroCliente(miVentanaRegistroCliente);
		clienteController.setMiVentanaBuscarCliente(miVentanaBuscarCliente);
		clienteController.setClienteServ(miclienteServ);
		clienteController.setVideosController(videosController);
		
		videosController.setMiVentanaPrincipal(miVentanaPrincipal);
		videosController.setMiVentanaRegistroVideos(miVentanaRegistroVideos);
		videosController.setMiVentanaBuscarVideos(miVentanaBuscarVideos);
		videosController.setVideosServ(mivideosServ);
				
		miVentanaPrincipal.setVisible(true);
	}

}

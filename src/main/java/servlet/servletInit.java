package servlet;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import model.Customer;
import model.Orders;
import service.customerService;
import service.customerServiceImpl;
import service.ordersService;
import service.ordersServiceImpl;

@WebServlet("/")
public class servletInit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private customerService customerServicio;

	@Inject
	private ordersService orderServicio;

	public void init() {
		customerServicio = new customerServiceImpl();
		orderServicio = new ordersServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**************** Menu **************************************/
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(req, resp);
			break;

		case "/newOrder":

			try {
				showNewFormOrder(req, resp);
			} catch (IOException | ServletException | JAXBException e1) {
				e1.printStackTrace();
			}

			break;

		case "/insert":
			try {
				System.out.println("paracrear");

				insertCustomer(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "/insertOrder":
			try {
				System.out.println("paracrear");

				insertOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "/delete":
			try {
				deleteCustomer(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/deleteOrder":
			try {
				deleteOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/edit":
			try {
				showEdit(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/editOrder":
			try {
				System.out.print("PAraActualizarOrder");
				showEditOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/update":
			try {
				System.out.println("paraACtualizarCustomer");
				updateCustomer(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/updateOrder":
			try {
				System.out.println("paraACtualizarOrder");
				updateOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/toCustomer":
			try {
				System.out.println("ListandoCustomerForOrders");
				listAll(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/listOrder":
			try {
				System.out.println("paraACtualizarOrder");
				listOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/listarOrders":
			try {
				System.out.println("ListandoOrdenes");
				listarOrdenes(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/listarCustomers":
			try {
				System.out.println("ListandoCustomers");
				listarCustomers(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:

			try {
				// listAll(req, resp);
				inicio(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}

	}

	/***********************************************************/

	public void inicio(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");
		dispatcher.forward(request, response);

	}

	public void listarOrdenes(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Orders> listorder = null;
		try {
			listorder = orderServicio.findAllOrders();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		request.setAttribute("listorder", listorder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-list.jsp");
		dispatcher.forward(request, response);

	}

	public void listarCustomers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Customer> listcustomer = null;
		try {
			listcustomer = customerServicio.findAllCustomers();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		request.setAttribute("listcustomer", listcustomer);

		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		dispatcher.forward(request, response);

	}

	public void listAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Customer> listcustomer = null;
		List<Orders> listorder = null;
		try {
			listorder = orderServicio.findAllOrders();
			listcustomer = customerServicio.findAllCustomers();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		request.setAttribute("listcustomer", listcustomer);

		request.setAttribute("listorder", listorder);

	}

	public void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Orders> listorder = null;
		try {
			listorder = orderServicio.findAllOrders();

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		request.setAttribute("listorder", listorder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);

	}

	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id_customer"));
		try {
			customerServicio.borrar(id);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listarCustomers");
	}

	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id_order"));
		try {
			orderServicio.borrar(id);
			System.out.println("psotDelete");
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("a lsitar");
		response.sendRedirect("listarOrders");
	}

	public void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		Long id = Long.parseLong(request.getParameter("id_customer"));

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Customer customer = new Customer(id, name, surname);
		System.out.println("c" + customer);

		try {
			customerServicio.actualizar(customer);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listarCustomers");
	}

	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		Long id = Long.parseLong(request.getParameter("id_order"));

		String item = request.getParameter("item");

		double precio = Double.parseDouble(request.getParameter("precio"));
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		Orders persona = new Orders(id, item, precio, customer_id);

		try {
			orderServicio.actualizar(persona);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listarOrders");
	}

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
		dispatcher.forward(request, response);

	}

	public void showNewFormOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, JAXBException {
		List<Customer> listcustomer = null;
		try {
			listcustomer = customerServicio.findAllCustomers();

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		request.setAttribute("listcustomer", listcustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
		dispatcher.forward(request, response);

	}

	public void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Customer newcustomer = new Customer(name, surname);

		try {

			customerServicio.insertar(newcustomer);
		} catch (InterruptedException | ExecutionException | TimeoutException | IOException | JAXBException e) {

			e.printStackTrace();
		}
		response.sendRedirect("listarCustomers");

	}

	public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String item = request.getParameter("item");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		Orders newOrden = new Orders(item, precio, customer_id);
	

		try {

			System.out.println("here insert order>>>>" + newOrden.getItem());
			orderServicio.insertar(newOrden);
		} catch (InterruptedException | ExecutionException | TimeoutException | IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listarOrders");

	}

	public void showEdit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {

		int id = Integer.parseInt(request.getParameter("id_customer"));
	
		Customer p = new Customer();

		try {
			p = customerServicio.findCustomer(id);
		
			request.setAttribute("customer", p);

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		} finally {
	
			RequestDispatcher ds = request.getRequestDispatcher("customer-form.jsp");
			ds.forward(request, response);

		}

	}

	public void showEditOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("id_order"));
	
		Orders p = new Orders();
		List<Customer> listcustomer = null;
		try {
			listcustomer = customerServicio.findAllCustomers();
			p = orderServicio.findOrder(id);
			System.out.println("orden>>>>" + p);
			request.setAttribute("order", p);

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		} finally {
			// System.out.println("req"+request.getAttribute(getServletInfo()));
			request.setAttribute("listcustomer", listcustomer);
			RequestDispatcher ds = request.getRequestDispatcher("order-form.jsp");
			ds.forward(request, response);

		}
	}
}

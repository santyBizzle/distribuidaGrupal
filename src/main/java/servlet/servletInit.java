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
			showNewFormOrder(req, resp);
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
				showEditOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/update":
			try {
				System.out.println("paraACtualizar");
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

		case "/listOrder":
			try {
				System.out.println("paraACtualizarOrder");
				listOrder(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:

			try {
				listAll(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}

	}

	/***********************************************************/

	public void listAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Customer> listcustomer = null;
		List<Orders> listorder = null;
		try {
			listorder = orderServicio.findAllOrders();
			listcustomer = customerServicio.findAllCustomers();
			System.out.println("here>>>>" + listorder.get(0));
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		request.setAttribute("listcustomer", listcustomer);

		request.setAttribute("listorder", listorder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);

	}

	public void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		List<Orders> listorder = null;
		try {
			listorder = orderServicio.findAllOrders();

			System.out.println("here>>>>" + listorder.get(0));
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
		System.out.println("here>>>>" + id);
		try {
			customerServicio.borrar(id);
			System.out.println("psotDelete");
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("a lsitar");
		response.sendRedirect("list");
	}

	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id_order"));
		System.out.println("here>>>>" + id);
		try {
			orderServicio.borrar(id);
			System.out.println("psotDelete");
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("a lsitar");
		response.sendRedirect("list");
	}

	public void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		Long id = Long.parseLong(request.getParameter("id_customer"));

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Customer customer = new Customer(id, name, surname);
		System.out.println("person" + customer);

		try {
			customerServicio.actualizar(customer);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		Long id = Long.parseLong(request.getParameter("id_order"));

		System.out.println("actualizarOderId>>" + id);
		String item = request.getParameter("item");
		int precio = Integer.parseInt(request.getParameter("precio"));
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		Orders persona = new Orders(id, item, precio, customer_id);

		try {
			orderServicio.actualizar(persona);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
		dispatcher.forward(request, response);

	}

	public void showNewFormOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
		dispatcher.forward(request, response);

	}

	public void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Customer newcustomer = new Customer(name, surname);

		try {
			System.out.println("here insert>>>>" + newcustomer.getName());
			customerServicio.insertar(newcustomer);
		} catch (InterruptedException | ExecutionException | TimeoutException | IOException | JAXBException e) {

			e.printStackTrace();
		}
		response.sendRedirect("list");

	}

	public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		String item = request.getParameter("item");
		int precio = Integer.parseInt(request.getParameter("precio"));
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		Orders newOrden = new Orders(item, precio, customer_id);
		System.out.println("order precio>>>>" + newOrden.getPrecio());

		try {
			System.out.println("here insert order>>>>" + newOrden.getItem());
			orderServicio.insertar(newOrden);
		} catch (InterruptedException | ExecutionException | TimeoutException | IOException | JAXBException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");

	}

	public void showEdit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {

		int id = Integer.parseInt(request.getParameter("id_customer"));
		System.out.println("here>>>>" + id);
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
		System.out.println("ddasd>>>>");
		int id = Integer.parseInt(request.getParameter("id_order"));
		System.out.println("here>>>>" + id);
		Orders p = new Orders();

		try {
			p = orderServicio.findOrder(id);
			System.out.println("orden>>>>" + p);
			request.setAttribute("order", p);

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		} finally {
			// System.out.println("req"+request.getAttribute(getServletInfo()));

			RequestDispatcher ds = request.getRequestDispatcher("order-form.jsp");
			ds.forward(request, response);

		}
	}
}

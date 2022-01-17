package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();


        String dateExp = req.getParameter("medicineExpirationDate");
        String dateArr = req.getParameter("arrivalDate");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Double arrival = Double.parseDouble(req.getParameter("productArrival"));

//        Medicine medicine = new Medicine.Builder()
//                .commercialName(req.getParameter("commercialName"))
//                .internationalName(req.getParameter("internationalName"))
//                .medicineDose(Integer.parseInt(req.getParameter("medicineDose")))
//                .medicineForm(req.getParameter("medicineForm"))
//                .diseaseGroup(DiseaseGroup.valueOf(req.getParameter("diseaseGroup")))
//                .invoiceNumber(req.getParameter("invoiceNumber"))
//                .medicineExpirationDate(formatter.parse(req.getParameter("medicineExpirationDate")))
//                .isPrescriptionRequired(Boolean.parseBoolean(req.getParameter("prescriptionRequired")))
//                .packagePrice(Double.parseDouble(req.getParameter("packagePrice")))
//                .producer(medicinesService.getProducerByName(req.getParameter("producerName")))
//                .arrivalDate(formatter.parse(req.getParameter("arrivalDate")))
//                .productArrival(arrival)
//                .productBalance(arrival)
//                .serialNumber(req.getParameter("serialNumber"))
//                .build();
//
//        Order order = new Order.Builder()
//                .client(getUserById(rs.getInt(CLIENTS_ID)))
//                .pharmacist(getUserById(rs.getInt(PHARMACISTS_ID)))
//                .deliveryTime(new java.util.Date(rs.getDate(DELIVERY_TIME).getTime()))
//                .orderStatus(OrderStatus.valueOf(rs.getString(ORDER_STATUS)))
//                .paymentStatus(PaymentStatus.valueOf(rs.getString(PAYMENT_STATUS)))
//                .build();
//
//        try {
//            medicinesService.addMedicine(medicine);

//        } catch (IllegalArgumentException e) {
//            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
//            return;
//        }
//        HttpSession session = req.getSession(true);
//        session.setAttribute("user", user);
        resp.sendRedirect("/pharmacy/controller?command=GO_TO_MEDICINE_FORM&successfulAdding=true");

    }
}

package com.tony.src; /**
 * Author : Zhilu Tang
 * Date: 11-30-2015
 */

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculatorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SIMPLE_JSP_WITHOUT_JSTL = "calculator.jsp";
    private static final String SIMPLE_JSP_WITH_JSTL = "calculator_jstl.jsp";
    private static final String SIMPLE_JSP = SIMPLE_JSP_WITH_JSTL;


    private FormBeanFactory<CalculatorForm> calculatorFormBeanFactory = FormBeanFactory.getInstance(CalculatorForm.class);

    public void init() throws ServletException {
        System.out.println("CalculatorServlet has been initialized and it's only initialized once.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doCalculate(request, response, true);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doCalculate(request, response, false);

    }

    private void doCalculate(HttpServletRequest request, HttpServletResponse response, boolean isPost) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (!isPost) {
            RequestDispatcher d = request.getRequestDispatcher(SIMPLE_JSP);
            d.forward(request, response);
            return;
        }

        List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);

        CalculatorForm calculatorForm;
        try {
            calculatorForm = calculatorFormBeanFactory.create(request);
            request.setAttribute("form", calculatorForm);

            if (!calculatorForm.isPresent()) {
                RequestDispatcher d = request.getRequestDispatcher(SIMPLE_JSP);
                d.forward(request, response);
                return;
            }

            errors.addAll(calculatorForm.getValidationErrors());

            if (errors.size() != 0) {
                RequestDispatcher d = request.getRequestDispatcher(SIMPLE_JSP);
                d.forward(request, response);
                return;
            }


            request.setAttribute("xValue", calculatorForm.getParameterX());
            request.setAttribute("yValue", calculatorForm.getParameterY());
            request.setAttribute("resultValue", calculatorForm.formatAnswer());
            RequestDispatcher d = request.getRequestDispatcher(SIMPLE_JSP);
            d.forward(request, response);
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher(SIMPLE_JSP);
            d.forward(request, response);
        }
    }


}
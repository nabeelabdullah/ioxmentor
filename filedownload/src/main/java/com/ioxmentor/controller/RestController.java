package com.ioxmentor.controller;


import com.ioxmentor.entity.*;
import com.ioxmentor.enums.LoginStatus;
import com.ioxmentor.dto.LoginDTO;
import com.ioxmentor.enums.PaymentStatus;
import com.ioxmentor.enums.SignUpStatus;
import com.ioxmentor.payment.JavaIntegrationKit;
import com.ioxmentor.repo.CourseRepo;
import com.ioxmentor.repo.EnrollRepo;
import com.ioxmentor.repo.LoginRepo;
import com.ioxmentor.service.Account;
import com.ioxmentor.service.CourseService;
import com.ioxmentor.service.EnrollService;
import com.ioxmentor.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ioxmentor.repo.UserRepo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * created By @Nabeel 08-Oct-2017
 **/

@Controller
@RequestMapping(value = "/user")
public class RestController {

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OfferService offerService;

    public void homeHeader(Model model, HttpServletRequest request) {
        model.addAttribute("isLogin", "0");
        if (request.getAttribute("userId") != null) {
            Long userId = Long.parseLong(request.getAttribute("userId").toString());
            model.addAttribute("isLogin", "1");
            User user = userRepo.findOne(userId);
            model.addAttribute("name", user.getUserName());
            model.addAttribute("userId", userId);
        }
    }


    @RequestMapping(value = "/logout")
    public String logOut(Model view, HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("tokenId") != null) {
            Long tokenId = Long.parseLong(request.getAttribute("tokenId").toString());
            loginRepo.delete(tokenId);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "{cId}/offer")
    public String login(Model view, HttpServletRequest request, @PathVariable Long cId, @RequestParam String coupon) {
        Enroll enroll = enrollService.applyCoupon(Long.parseLong(request.getAttribute("userId").toString()), cId, coupon);
        return "redirect:/user/" + cId + "/paymentForm?redirect=self";
    }

    @RequestMapping(value = "{cId}/deleteOffer")
    public String deleteOffer(HttpServletRequest request, @PathVariable Long cId) {
        enrollService.deleteCoupon(Long.parseLong(request.getAttribute("userId").toString()), cId);
        return "redirect:/user/" + cId + "/paymentForm?redirect=self";
    }

    @RequestMapping(value = "{cId}/paymentForm")
    public String getPaymentForm(Model view, HttpServletRequest request, @PathVariable Long cId) {
        Enroll enroll = enrollService.enroll(Long.parseLong(request.getAttribute("userId").toString()), cId, "");
        Course course = courseService.getCourseById(cId);
        User user = userRepo.findOne(Long.parseLong(request.getAttribute("userId").toString()));
        view.addAttribute("result", "You have Added for the Course .Find details below");
        view.addAttribute("amount", course.getBasePrice());
        view.addAttribute("email", user.getEmail());
        view.addAttribute("title", course.getCourseTitle());
        view.addAttribute("name", user.getUserName());
        view.addAttribute("enrollId", enroll.getId());
        view.addAttribute("contact", user.getContact());
        view.addAttribute("code", enroll.getCoupon());
        view.addAttribute("amountToPaid", enroll.getAmmountToBePaid());
        view.addAttribute("couponApplied", enroll.getCoupon() != null);
        view.addAttribute("surl", "http://52.73.159.240:8080/payusuccess");
        view.addAttribute("furl", "http://52.73.159.240:8080/payufailed");
        homeHeader(view, request);
        return "payuform";
    }


    @ResponseBody
    @RequestMapping(value = "/payu", method = RequestMethod.POST)
    public String payment(HttpServletRequest request, HttpServletResponse response) {
        try {
            JavaIntegrationKit integrationKit = new JavaIntegrationKit();
            Map<String, String> values = integrationKit.hashCalMethod(request, response);
            System.out.println("values " + values);
            String htmlResponse = "<html> <body> \n"
                    + "      \n"
                    + "  \n"
                    + "  \n"
                    + "  \n" + "<div style=\"visibility:hidden\">"
                    + "        <form id=\"payuform\" action=\"" + values.get("action") + "\"  name=\"payuform\" method=POST >\n"
                    + "      <input type=\"hidden\" name=\"key\" value=" + values.get("key").trim() + ">"
                    + "      <input type=\"hidden\" name=\"hash\" value=" + values.get("hash").trim() + ">"
                    + "      <input type=\"hidden\" name=\"txnid\" value=" + values.get("txnid").trim() + ">"
                    + "      <table>\n"
                    + "        <tr>\n"
                    + "          <td><b>Mandatory Parameters</b></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "         <td>Amount: </td>\n"
                    + "          <td><input name=\"amount\" value=" + values.get("amount").trim() + " /></td>\n"
                    + "          <td>First Name: </td>\n"
                    + "          <td><input name=\"firstname\" id=\"firstname\" value=" + values.get("firstname").trim() + " /></td>\n"
                    + "        <tr>\n"
                    + "          <td>Email: </td>\n"
                    + "          <td><input name=\"email\" id=\"email\" value=" + values.get("email").trim() + " /></td>\n"
                    + "          <td>Phone: </td>\n"
                    + "          <td><input name=\"phone\" value=" + values.get("phone") + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Product Info: </td>\n"
                    + "<td><input name=\"productinfo\" value=" + values.get("productinfo").trim() + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Success URI: </td>\n"
                    + "          <td colspan=\"3\"><input name=\"surl\"  size=\"64\" value=" + values.get("surl") + "></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Failure URI: </td>\n"
                    + "          <td colspan=\"3\"><input name=\"furl\" value=" + values.get("furl") + " size=\"64\" ></td>\n"
                    + "        </tr>\n"
                    + "\n"
                    + "        <tr>\n"
                    + "          <td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>\n"
                    + "        </tr>\n"
                    + "             <tr>\n"
                    + "          <td><b>Optional Parameters</b></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Last Name: </td>\n"
                    + "          <td><input name=\"lastname\" id=\"lastname\" value=" + values.get("lastname") + " ></td>\n"
                    + "          <td>Cancel URI: </td>\n"
                    + "          <td><input name=\"curl\" value=" + values.get("curl") + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Address1: </td>\n"
                    + "          <td><input name=\"address1\" value=" + values.get("address1") + " ></td>\n"
                    + "          <td>Address2: </td>\n"
                    + "          <td><input name=\"address2\" value=" + values.get("address2") + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>City: </td>\n"
                    + "          <td><input name=\"city\" value=" + values.get("city") + "></td>\n"
                    + "          <td>State: </td>\n"
                    + "          <td><input name=\"state\" value=" + values.get("state") + "></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Country: </td>\n"
                    + "          <td><input name=\"country\" value=" + values.get("country") + " ></td>\n"
                    + "          <td>Zipcode: </td>\n"
                    + "          <td><input name=\"zipcode\" value=" + values.get("zipcode") + " ></td>\n"
                    + "        </tr>\n"
                    + "          <td>UDF1: </td>\n"
                    + "          <td><input name=\"udf1\" value=" + values.get("udf1") + "></td>\n"
                    + "          <td>UDF2: </td>\n"
                    + "          <td><input name=\"udf2\" value=" + values.get("udf2") + "></td>\n"
                    + " <td><input name=\"hashString\" value=" + values.get("hashString") + "></td>\n"
                    + "          <td>UDF3: </td>\n"
                    + "          <td><input name=\"udf3\" value=" + values.get("udf3") + " ></td>\n"
                    + "          <td>UDF4: </td>\n"
                    + "          <td><input name=\"udf4\" value=" + values.get("udf4") + " ></td>\n"
                    + "          <td>UDF5: </td>\n"
                    + "          <td><input name=\"udf5\" value=" + values.get("udf5") + " ></td>\n"
                    + "          <td>PG: </td>\n"
                    + "          <td><input name=\"pg\" value=" + values.get("pg") + " ></td>\n"
                    + "        <td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>\n"
                    + "      \n"
                    + "    \n"
                    + "      </table>\n"
                    + "    </form>\n"
                    + " <script> "
                    + " document.getElementById(\"payuform\").submit(); "
                    + " </script> "
                    + "       </div>   "
                    + "  \n"
                    + "  </body>\n"
                    + "</html>";
            return htmlResponse;

        } catch (Exception er) {

            er.printStackTrace();
        }
        return null;
    }

}

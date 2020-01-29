package cs544.exercise19;

import cs544.sample.NoSuchResourceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Resource
    private IBookDao bookDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "book/bookList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("book", new Book());
        return "/book/addBook";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(@Valid Book book, BindingResult result) {
        String view = "redirect:/books/";
        if(!result.hasErrors())
            bookDao.add(book);
        else
            view = "/book/addBook";
        return view;
    }

    @RequestMapping(value = "/{bookID}", method = RequestMethod.GET)
    public String get(@PathVariable int bookID, Model model) {
        model.addAttribute("book",bookDao.get(bookID));
        return "book/bookDetail";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(Book book, @PathVariable int id) {
        bookDao.update(id, book);
        return "redirect:/books/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int bookId) {
        bookDao.delete(bookId);
        return "redirect:/books/";
    }

    @ExceptionHandler(value = NoSuchResourceException.class)
    public ModelAndView handle(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.getModel().put("e", e);
        mav.setViewName("noSuchResource");
        return mav;
    }
}

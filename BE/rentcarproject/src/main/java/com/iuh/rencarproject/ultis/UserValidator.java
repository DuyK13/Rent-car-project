package com.iuh.rencarproject.ultis;

//@Component
//public class UserValidator implements Validator {
//
//	@Autowired
//	private IUserService userService;
//
//	@Override
//	public boolean supports(Class<?> aClass) {
//		return User.class.equals(aClass);
//	}
//
//	@Override
//	public void validate(Object o, Errors errors) {
//		User user = (User) o;
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
//		if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
//			errors.rejectValue("username", "Size.userForm.username");
//		}
//
//		if (userService.findByUserName(user.getUserName()) != null) {
//
//			errors.rejectValue("username", "Duplicate.userForm.username");
//		}
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//		if (user.getPassWord().length() < 8 || user.getPassWord().length() > 32) {
//			errors.rejectValue("password", "Size.userForm.password");
//		}
//
//		if (!user.getPassWordComfirm().equals(user.getPassWord())) {
//			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//		}
//	}
//}

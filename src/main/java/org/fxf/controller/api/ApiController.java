package org.fxf.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.fxf.bean.Page;
import org.fxf.constant.ApiCodeEnum;
import org.fxf.dto.AdDto;
import org.fxf.dto.ApiCodeDto;
import org.fxf.dto.BusinessDto;
import org.fxf.dto.BusinessListDto;
import org.fxf.dto.CommentForSubmitDto;
import org.fxf.dto.CommentListDto;
import org.fxf.dto.OrderForBuyDto;
import org.fxf.dto.OrdersDto;
import org.fxf.service.AdService;
import org.fxf.service.BusinessService;
import org.fxf.service.CommentService;
import org.fxf.service.MemberService;
import org.fxf.service.OrdersService;
import org.fxf.util.CommonUtil;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private AdService adService;
	
	@Resource
	private BusinessService businessService;
	
	@Resource
	private MemberService memberService;

	@Resource
	private OrdersService ordersService;
	
	@Resource
	private CommentService commentService;
	
	@Value("${ad.number}")
	private int adNumber;
	
	@Value("${businessHome.number}")
	private int businessHomeNumber;
	
	/**
	 * 首页 —— 广告（超值特惠）
	 */
	@RequestMapping(value="/homead",method=RequestMethod.GET)
	public List<AdDto> homead() {
		AdDto adDto=new AdDto();
		adDto.getPage().setPageNumber(adNumber);
		return adService.searchByPage(adDto);
	}
	
	/*@RequestMapping(value="/homead",method=RequestMethod.GET)
	public List<Ad> homead() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper=new ObjectMapper();
		String s="[{\"title\":\"暑假5折\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796.png\",\"link\":\"http://www.imooc.com/wap/index\"},"
				+ "{\"title\":\"特价出国\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191648124-298129318.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"亮亮车\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191653983-1962772127.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"学钢琴\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191700420-1584459466.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"电影\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191706733-367929553.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"旅游热线\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191713186-495002222.png\",\"link\":\"http://www.imooc.com/wap/index\"}]";
		return mapper.readValue(s, new TypeReference<List<Ad>>(){});
	}*/
	
	/**
	 * 首页 —— 推荐列表（猜你喜欢）
	 */
	@RequestMapping(value="/homelist/{city}/{page.currentPage}",method=RequestMethod.GET)
	public BusinessListDto homelist(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.searchByPageForApi(businessDto);
	}
	
	/**
	 * 搜索结果页 - 搜索结果 - 三个参数
	 */
	@RequestMapping(value="/search/{page.currentPage}/{city}/{category}/{keyword}",method=RequestMethod.GET)
	public BusinessListDto searchByKeyword(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.searchByPageForApi(businessDto);
	}
	
	/**
	 * 搜索结果页 - 搜索结果 - 两个参数
	 */
	@RequestMapping(value="/search/{page.currentPage}/{city}/{category}",method=RequestMethod.GET)
	public BusinessListDto search(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.searchByPageForApi(businessDto);
	}
	
	/**
	 * 详情页 - 商户信息
	 */
	@RequestMapping(value="/detail/info/{id}",method=RequestMethod.GET)
	public BusinessDto detail(@PathVariable("id") Long id){
		return businessService.getById(id);
	}
	
	/*@RequestMapping(value="/homelist/{city}/{page}",method=RequestMethod.GET)
	public BusinessList homelist() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper=new ObjectMapper();
		String s="{\"hasMore\":true,\"data\":"
				+ "[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大大\",\"subTitle\":\"叫我汉堡大大，还你多彩口味\",\"price\":\"28\",\"distance\":\"120\",\"mumber\":\"389\",\"id\":\"8372918325568681\"},"
				+ "{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"北京开源饭店\",\"subTitle\":\"[望京]自助晚餐\",\"price\":\"98\",\"distance\":\"140\",\"mumber\":\"689\",\"id\":\"7690284842905781\"},"
				+ "{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\"title\":\"服装定制\",\"subTitle\":\"原价xx元，现价xx元，可修改一次\",\"price\":\"1980\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"6225092208513121\"},"
				+ "{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\"title\":\"婚纱摄影\",\"subTitle\":\"免费试穿，拍照留念\",\"price\":\"2899\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"7480323173680299\"},"
				+ "{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣串串烧\",\"subTitle\":\"双人免费套餐等你抢购\",\"price\":\"0\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"938318856260896\"}]}";
		return mapper.readValue(s, new TypeReference<BusinessList>(){});
	}*/
	
	/**
	 * 详情页 - 用户评论
	 */
	@RequestMapping(value="/detail/comment/{currentPage}/{businessId}",method=RequestMethod.GET)
	public CommentListDto detail(@PathVariable("businessId") Long businessId,Page page){
		return commentService.getListByBusinessId(businessId, page);
	}
	
	/**
	 * 提交评论
	 */
	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	public ApiCodeDto submitComment(CommentForSubmitDto dto){
		ApiCodeDto result;
		// TODO 需要完成的步骤：
		// 1、校验登录信息：token、手机号
		Long phone = memberService.getPhone(dto.getToken());
		if (phone != null && phone.equals(dto.getUsername())) {
			// 2、根据手机号取出会员ID
			Long memberId = memberService.getIdByPhone(phone);
			// 3、根据提交上来的订单ID获取对应的会员ID，校验与当前登录的会员是否一致
			OrdersDto ordersDto = ordersService.getById(memberId);
			if (ordersDto.getMemberId().equals(memberId)) {
				// 4、保存评论
				commentService.add(dto);
				result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
				// TODO
				// 5、还有一件重要的事未做
			}else {
				result = new ApiCodeDto(ApiCodeEnum.NO_AUTH);
			}
		}else {
			result = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
		}
		return result;
	}
	
	/**
	 * 根据手机号下发短信验证码
	 */
	@RequestMapping(value="/sms",method=RequestMethod.POST)
	public ApiCodeDto sms(@RequestParam("username") Long username){
		ApiCodeDto dto;
		if (username != null && !"".equals(username)) {
			// 1、验证用户手机号是否存在（是否注册过）
			if (memberService.exists(username)) {
				// 2、生成6位随机数
				String code=String.valueOf(CommonUtil.random(6));
				// 3、保存手机号与对应的md5(6位随机数)（一般保存1分钟，1分钟后失效）
				if (memberService.saveCode(username, code)) {
					// 4、调用短信通道，将明文6位随机数发送到对应的手机上。
					if (!memberService.sendCode(username, code)) {
						dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
					}else{
						dto=new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(),code);
					}
				}else {
					dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
				}
			}else {
				dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
			}
		}else {
			dto = new ApiCodeDto(ApiCodeEnum.NO_UAERPHONE);
		}
		return dto;
	}
	
	/**
	 * 会员登录
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ApiCodeDto login(@RequestParam("username") Long username,@RequestParam("code") String code){
		ApiCodeDto dto;
		// 1、用手机号取出保存的md5(6位随机数)，能取到，并且与提交上来的code值相同为校验通过
		String saveCode=memberService.getCode(username);
		if (saveCode != null) {
			if (saveCode.equals(code)) {
				// 2、如果校验通过，生成一个32位的token
				String token=CommonUtil.getUUID();
				// 3、保存手机号与对应的token（一般这个手机号中途没有与服务端交互的情况下，保持10分钟）
				memberService.saveToken(token, username);
				// 4、将这个token返回给前端
				dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
				dto.setToken(token);
			}else{
				dto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
			}
		}else{
			dto=new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
		}
		return dto;
	}
	
	/**
	 * 订单列表
	 */
	@RequestMapping(value="/orderlist/{username}",method=RequestMethod.GET)
	public List<OrdersDto> orderlist(@PathVariable("username") Long username){
		// 根据手机号取出会员ID
		Long memberId = memberService.getIdByPhone(username);
		return ordersService.getListByMemberId(memberId);
	}
	
	/*@RequestMapping(value="/submitComment",method=RequestMethod.POST)
	public Map<String, Object> submitComment() {
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("errno", 0);
		result.put("msg", "ok");
		return result;
	}*/
	
	/**
	 * 买单
	 */
	@RequestMapping(value="/order",method=RequestMethod.POST)
	public ApiCodeDto order(OrderForBuyDto orderForBuyDto){
		ApiCodeDto dto;
		// 1、校验token是否有效（缓存中是否存在这样一个token，并且对应存放的会员信息（这里指的是手机号）与提交上来的信息一致）
		Long phone = memberService.getPhone(orderForBuyDto.getToken());
		if (phone != null && phone.equals(orderForBuyDto.getUsername())) {
			// 2、根据手机号获取会员主键
			Long memberId = memberService.getIdByPhone(phone);
			// 3、保存订单
			OrdersDto ordersDto = new OrdersDto();
			ordersDto.setNum(orderForBuyDto.getNum());
			ordersDto.setPrice(orderForBuyDto.getPrice());
			ordersDto.setBusinessId(orderForBuyDto.getId());
			ordersDto.setMemberId(memberId);
			ordersService.add(ordersDto);
			dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
			// 4、TODO 商户已售数量
			
		}else {
			dto = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
		}
		return dto;
	}

}

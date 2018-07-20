// package com.dong.buddy.yuyin.service;
//
// import java.io.File;
// import java.io.IOException;
// import java.util.UUID;
//
// import org.json.JSONObject;
// import org.springframework.util.StringUtils;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.dong.buddy.yuyin.util.ConvertYuyinUtil;
//
// import it.sauronsoftware.jave.AudioAttributes;
// import it.sauronsoftware.jave.Encoder;
// import it.sauronsoftware.jave.EncodingAttributes;
//
// public class YuyinService
// {
//
// /**
// * 实现文件上传
// *
// * @throws Exception
// */
// @RequestMapping(value = "/api/fileUpload", method = RequestMethod.POST)
// public String fileUpload(@RequestParam("fileName") MultipartFile file, @RequestParam("ostype")
// String ostype,
// @RequestParam("aiswitch") String aiswitch) throws Exception
// {
// UUID uuid = UUID.randomUUID();
// if (file.isEmpty())
// {
// return "false";
// }
// String fileName = file.getOriginalFilename();
// int size = (int) file.getSize();
// System.out.println(fileName + "-->" + size);
// File dest = null;
// if ("android".equals(ostype))
// {
// dest = new File(tempPath + "/src_" + uuid + ".amr");
// }
// else
// {
// dest = new File(tempPath + "/src_" + uuid + ".wav");
// }
// File target = dest;
//
// if (!dest.getParentFile().exists())
// { // 判断文件父目录是否存在
// dest.getParentFile().mkdir();
// }
// try
// {
// file.transferTo(dest); // 保存文件
//
// if ("android".equals(ostype))
// {
// Encoder encoder = new Encoder();
// target = new File(tempPath + "/des_" + uuid + ".wav");
// AudioAttributes audioAttr = new AudioAttributes();
// audioAttr.setChannels(1);
// audioAttr.setSamplingRate(16000);
//
// EncodingAttributes attrs = new EncodingAttributes();
// attrs.setFormat("wav");
// attrs.setDuration(60f);
// attrs.setOffset(0f);
// attrs.setAudioAttributes(audioAttr);
// try
// {
// encoder.encode(dest, target, attrs);
// }
// catch (it.sauronsoftware.jave.EncoderException e)
// {
// if (!e.getMessage().contains("Duration: N/A, bitrate: N/A"))
// {
// e.printStackTrace();
// }
// }
// }
// System.out.println("路径：" + target.getAbsolutePath());
// JSONObject convertAudio = ConvertYuyinUtil.convertAudio(target.getAbsolutePath(), "pcm");
// convertAudio.put("filePath", dest.getAbsolutePath());
// String quition = (String) convertAudio.get("result");
// if (!StringUtils.isEmpty(quition) && (quition.startsWith("开启") || quition.startsWith("打开"))
// && quition.endsWith("智能"))
// {
// aiswitch = "true";
// convertAudio.put("result", "已开启");
// convertAudio.put("aiswitch", aiswitch);
// }
// else
// {
// convertAudio.put("aiswitch", aiswitch);
// if ("true".equals(aiswitch) && !StringUtils.isEmpty(quition))
// {
// String semantic = semantic(quition);
// convertAudio.put("result", semantic);
// }
// }
//
// return convertAudio.toString();
// }
// catch (IllegalStateException e)
// {
// // TODO Auto-generated catch block
// e.printStackTrace();
// return "false";
// }
// catch (IOException e)
// {
// // TODO Auto-generated catch block
// e.printStackTrace();
// return "false";
// }
// }
//
// }

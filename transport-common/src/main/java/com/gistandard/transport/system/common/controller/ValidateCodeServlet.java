package com.gistandard.transport.system.common.controller;

import com.gistandard.transport.base.define.SystemDefine;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author yujie
 * @ClassName ValidateCodeServlet
 * @Description
 * @Version 1.0
 * @Date 2015-12-08
 */
public class ValidateCodeServlet extends HttpServlet {

    private int captchaType;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String captchaTypeStr = config.getInitParameter("captchaType");
        if(StringUtils.isNotEmpty(captchaTypeStr)){
            captchaType = NumberUtils.toInt(captchaTypeStr, 1);
        }
        else {
            captchaType = 1;
        }
    }

    Color getRandColor(Random random, int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置页面不缓存
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        //创建随机类实例
        Random random = new Random();
        //定义图片尺寸
        int width = 90 * this.captchaType, height = (this.captchaType == 1) ? 35 : 25;
        //创建内存图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取图形上下文
        Graphics g = image.getGraphics();
        //设定背景色
        g.setColor(this.getRandColor(random, 200, 250));
        //设定图形的矩形坐标及尺寸
        g.fillRect(0, 0, width, height);

        String sRand = "";
        if (this.captchaType == 1) {
            //图片背景随机产生50条干扰线作为噪点
            g.setColor(this.getRandColor(random, 160, 200));
            g.setFont(new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, 30));
            for (int i = 0; i < 50; i++) {
                int x11 = random.nextInt(width);
                int y11 = random.nextInt(height);
                int x22 = random.nextInt(width);
                int y22 = random.nextInt(height);
                g.drawLine(x11, y11, x11 + x22, y11 + y22);
            }
            //取随机产生的4个数字作为验证码
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for (int i = 0; i < 4; i++) {
                int rand = random.nextInt(62);
                sRand += str.charAt(rand);
                g.setColor(this.getRandColor(random, 10, 150));
                //将此数字画到图片上
                g.drawString(str.charAt(rand) + "", 20 * i + 10, 28);
            }
        } else {
            //设定备选汉字
            StringBuilder builder = new StringBuilder();
            builder.append("\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740")
                    .append("\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b")
                    .append("\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1")
                    .append("\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001")
                    .append("\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1")
                    .append("\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1")
                    .append("\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0")
                    .append("\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee")
                    .append("\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168")
                    .append("\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d")
                    .append("\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c")
                    .append("\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0")
                    .append("\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f")
                    .append("\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf")
                    .append("\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597")
                    .append("\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3")
                    .append("\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be")
                    .append("\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173")
                    .append("\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757")
                    .append("\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752")
                    .append("\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6")
                    .append("\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531")
                    .append("\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5")
                    .append("\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4")
                    .append("\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c")
                    .append("\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a")
                    .append("\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834")
                    .append("\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce")
                    .append("\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559")
                    .append("\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645")
                    .append("\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165")
                    .append("\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c")
                    .append("\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6");
            //图片背景增加噪点
            g.setColor(this.getRandColor(random, 160, 200));
            g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            for (int i = 0; i < 6; i++) {
                g.drawString("*********************************************", 0, 5 * (i + 2));
            }
            //设定验证码汉字的备选字体{"宋体", "新宋体", "黑体", "楷体", "隶书"}
            String[] fontTypes = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};
            //取随机产生的4个汉字作为验证码
            for (int i = 0; i < 4; i++) {
                int start = random.nextInt(builder.length());
                String rand = builder.substring(start, start + 1);
                sRand += rand;
                g.setColor(this.getRandColor(random, 10, 150));
                g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 18 + random.nextInt(4)));
                //将此汉字画到图片上
                g.drawString(rand, 24 * i + 10 + random.nextInt(8), 24);
            }
        }
        HttpSession session = req.getSession();
        //将验证码存入SESSION
        session.setAttribute(SystemDefine.SESSION_ATTR_VALIDATE_CODE, sRand);
        //图像生效
        g.dispose();
        //输出图像到页面
        ImageIO.write(image, "PNG", resp.getOutputStream());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

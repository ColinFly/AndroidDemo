package cn.etouch.ecalendar.common.libs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DES {
    private static final String ALGORITHM = "DESede";
    Cipher cipher_de = null;
    Cipher cipher_en = null;

    static final class AnonymousClass1 implements Runnable {
        final /* synthetic */ String val$action;
        final /* synthetic */ String val$btntext;
        final /* synthetic */ Context val$ctx;
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ boolean val$isMainLoop;
        final /* synthetic */ String val$str;
        final /* synthetic */ String val$title;

        AnonymousClass1(Context context, String str, String str2, String str3, String str4, boolean z, Handler handler) {
            this.val$ctx = context;
            this.val$title = str;
            this.val$str = str2;
            this.val$btntext = str3;
            this.val$action = str4;
            this.val$isMainLoop = z;
            this.val$handler = handler;
        }

        public void run() {
            Builder builder = new Builder(this.val$ctx);
            builder.setTitle(this.val$title);
            builder.setMessage(this.val$str);
            builder.setPositiveButton(this.val$btntext, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(AnonymousClass1.this.val$action));
                        intent.setFlags(268435456);
                        AnonymousClass1.this.val$ctx.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    if (!AnonymousClass1.this.val$isMainLoop) {
                        try {
                            AnonymousClass1.this.val$handler.getLooper().quit();
                        } catch (Exception e) {
                        }
                    }
                }
            });
            AlertDialog create = builder.create();
            create.getWindow().setType(2003);
            create.show();
        }
    }

    private DES(String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            Key generateSecret = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(new DESedeKeySpec(str.getBytes("utf-8")));
            this.cipher_en = Cipher.getInstance(ALGORITHM);
            this.cipher_en.init(1, generateSecret, secureRandom);
            this.cipher_de = Cipher.getInstance(ALGORITHM);
            this.cipher_de.init(2, generateSecret, secureRandom);
        } catch (Exception e) {
        }
    }

    private String byte2hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append("0" + toHexString);
            } else {
                stringBuffer.append(toHexString);
            }
        }
        return stringBuffer.toString();
    }

    private String decrypt(String str) {
        try {
            return new String(this.cipher_de.doFinal(hex2byte(str)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private String encrypt(String str) {
        try {
            str = byte2hex(this.cipher_en.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String getMD5(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            while (i < 16) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getSignInfo(Context context) {
        try {
            return getMD5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void getTheDate(Context context, String str, String str2, String str3, String str4) {
        try {
            boolean z = Looper.myLooper() == Looper.getMainLooper();
            if (!z) {
                try {
                    Looper.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Handler handler = new Handler();
            handler.post(new AnonymousClass1(context, str, str2, str3, str4, z, handler));
            if (!z) {
                try {
                    Looper.loop();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 0 || length % 2 == 1) {
            return null;
        }
        byte[] bArr = new byte[(length / 2)];
        int i = 0;
        while (i < trim.length()) {
            try {
                bArr[i / 2] = (byte) Integer.decode("0X" + trim.substring(i, i + 2)).intValue();
                i += 2;
            } catch (Exception e) {
                return null;
            }
        }
        return bArr;
    }
}
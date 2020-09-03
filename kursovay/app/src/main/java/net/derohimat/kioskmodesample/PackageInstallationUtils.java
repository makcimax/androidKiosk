package net.derohimat.kioskmodesample;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PackageInstallationUtils {
    public static final String ACTION_INSTALL_COMPLETE
            = "net.derohimat.kioskmodesample.INSTALL_COMPLETE";
    private static final String ACTION_UNINSTALL_COMPLETE
            = "net.derohimat.kioskmodesample.UNINSTALL_COMPLETE";

    public static boolean installPackage(Context context, InputStream in, String packageName)
            throws IOException {
        final PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        final PackageInstaller.SessionParams params = new PackageInstaller.SessionParams(
                PackageInstaller.SessionParams.MODE_FULL_INSTALL);
        params.setAppPackageName(packageName);
        // set params
        final int sessionId = packageInstaller.createSession(params);
        final PackageInstaller.Session session = packageInstaller.openSession(sessionId);
        final OutputStream out = session.openWrite("TestDPC", 0, -1);
        final byte[] buffer = new byte[65536];
        int c;
        while ((c = in.read(buffer)) != -1) {
            out.write(buffer, 0, c);
        }
        session.fsync(out);
        in.close();
        out.close();
        session.commit(createInstallIntentSender(context, sessionId));
        return true;
    }

    public static void uninstallPackage(Context context, String packageName) {
        final PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        packageInstaller.uninstall(packageName, createUninstallIntentSender(context, packageName));
    }

    private static IntentSender createInstallIntentSender(Context context, int sessionId) {
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId,
                new Intent(ACTION_INSTALL_COMPLETE), 0);
        return pendingIntent.getIntentSender();
    }

    private static IntentSender createUninstallIntentSender(Context context, String packageName) {
        final Intent intent = new Intent(ACTION_UNINSTALL_COMPLETE);
        intent.putExtra(Intent.EXTRA_PACKAGE_NAME, packageName);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, 0);
        return pendingIntent.getIntentSender();
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, PackageInstallationUtils.class);
        context.startActivity(intent);
    }
}

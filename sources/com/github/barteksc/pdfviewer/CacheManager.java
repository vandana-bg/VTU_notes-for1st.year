package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.util.Constants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class CacheManager {
    private final PriorityQueue<PagePart> activeCache;
    private final PagePartComparator comparator;
    private final Object passiveActiveLock = new Object();
    private final PriorityQueue<PagePart> passiveCache;
    private final List<PagePart> thumbnails;

    public CacheManager() {
        PagePartComparator pagePartComparator = new PagePartComparator();
        this.comparator = pagePartComparator;
        this.activeCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, pagePartComparator);
        this.passiveCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, pagePartComparator);
        this.thumbnails = new ArrayList();
    }

    public void cachePart(PagePart part) {
        synchronized (this.passiveActiveLock) {
            makeAFreeSpace();
            this.activeCache.offer(part);
        }
    }

    public void makeANewSet() {
        synchronized (this.passiveActiveLock) {
            this.passiveCache.addAll(this.activeCache);
            this.activeCache.clear();
        }
    }

    private void makeAFreeSpace() {
        synchronized (this.passiveActiveLock) {
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.passiveCache.isEmpty()) {
                this.passiveCache.poll().getRenderedBitmap().recycle();
            }
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.activeCache.isEmpty()) {
                this.activeCache.poll().getRenderedBitmap().recycle();
            }
        }
    }

    public void cacheThumbnail(PagePart part) {
        synchronized (this.thumbnails) {
            if (this.thumbnails.size() >= Constants.Cache.THUMBNAILS_CACHE_SIZE) {
                this.thumbnails.remove(0).getRenderedBitmap().recycle();
            }
            this.thumbnails.add(part);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean upPartIfContained(int r12, int r13, float r14, float r15, android.graphics.RectF r16, int r17) {
        /*
            r11 = this;
            r1 = r11
            com.github.barteksc.pdfviewer.model.PagePart r0 = new com.github.barteksc.pdfviewer.model.PagePart
            r5 = 0
            r9 = 0
            r10 = 0
            r2 = r0
            r3 = r12
            r4 = r13
            r6 = r14
            r7 = r15
            r8 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.Object r3 = r1.passiveActiveLock
            monitor-enter(r3)
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.passiveCache     // Catch:{ all -> 0x003c }
            com.github.barteksc.pdfviewer.model.PagePart r0 = find(r0, r2)     // Catch:{ all -> 0x003c }
            r4 = r0
            r5 = 1
            if (r0 == 0) goto L_0x002e
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.passiveCache     // Catch:{ all -> 0x003c }
            r0.remove(r4)     // Catch:{ all -> 0x003c }
            r6 = r17
            r4.setCacheOrder(r6)     // Catch:{ all -> 0x0041 }
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.activeCache     // Catch:{ all -> 0x0041 }
            r0.offer(r4)     // Catch:{ all -> 0x0041 }
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            return r5
        L_0x002e:
            r6 = r17
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r0 = r1.activeCache     // Catch:{ all -> 0x0041 }
            com.github.barteksc.pdfviewer.model.PagePart r0 = find(r0, r2)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            return r5
        L_0x003c:
            r0 = move-exception
            r6 = r17
        L_0x003f:
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            throw r0
        L_0x0041:
            r0 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.CacheManager.upPartIfContained(int, int, float, float, android.graphics.RectF, int):boolean");
    }

    public boolean containsThumbnail(int userPage, int page, float width, float height, RectF pageRelativeBounds) {
        PagePart fakePart = new PagePart(userPage, page, (Bitmap) null, width, height, pageRelativeBounds, true, 0);
        synchronized (this.thumbnails) {
            for (PagePart part : this.thumbnails) {
                if (part.equals(fakePart)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static PagePart find(PriorityQueue<PagePart> vector, PagePart fakePart) {
        Iterator<PagePart> it = vector.iterator();
        while (it.hasNext()) {
            PagePart part = it.next();
            if (part.equals(fakePart)) {
                return part;
            }
        }
        return null;
    }

    public List<PagePart> getPageParts() {
        List<PagePart> parts;
        synchronized (this.passiveActiveLock) {
            parts = new ArrayList<>(this.passiveCache);
            parts.addAll(this.activeCache);
        }
        return parts;
    }

    public List<PagePart> getThumbnails() {
        List<PagePart> list;
        synchronized (this.thumbnails) {
            list = this.thumbnails;
        }
        return list;
    }

    public void recycle() {
        synchronized (this.passiveActiveLock) {
            Iterator<PagePart> it = this.passiveCache.iterator();
            while (it.hasNext()) {
                it.next().getRenderedBitmap().recycle();
            }
            this.passiveCache.clear();
            Iterator<PagePart> it2 = this.activeCache.iterator();
            while (it2.hasNext()) {
                it2.next().getRenderedBitmap().recycle();
            }
            this.activeCache.clear();
        }
        synchronized (this.thumbnails) {
            for (PagePart part : this.thumbnails) {
                part.getRenderedBitmap().recycle();
            }
            this.thumbnails.clear();
        }
    }

    class PagePartComparator implements Comparator<PagePart> {
        PagePartComparator() {
        }

        public int compare(PagePart part1, PagePart part2) {
            if (part1.getCacheOrder() == part2.getCacheOrder()) {
                return 0;
            }
            return part1.getCacheOrder() > part2.getCacheOrder() ? 1 : -1;
        }
    }
}

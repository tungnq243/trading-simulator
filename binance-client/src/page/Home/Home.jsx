import { Button } from '@/components/ui/button'
import React, { useState } from 'react'
import { AssetTable } from './AssetTable';
import { StockChart } from '../StockDetails/StockChart';
import { Avatar, AvatarImage } from '@/components/ui/avatar';
import { DotIcon } from 'lucide-react';

export const Home = () => {
    const [category, setCategory] = useState("all");

    const handleCategory = (value) => {
        setCategory(value)
    }
    return (
        <div className='relative'>
            <div className='lg:flex'>
                <div className='lg:w-[50%] lg:border-r'>
                    <div className='p-3 flex items-center gap-4'>
                        <Button onClick={() => handleCategory("all")} 
                        variant={category == "all" ? "default" : "outline"} className='rounded-full'>All</Button>

                        <Button onClick={() => handleCategory("top50")} 
                        variant={category == "top50" ? "default" : "outline"} className='rounded-full'>Hot Coins</Button>

                        <Button onClick={() => handleCategory("topGainers")} 
                        variant={category == "topGainers" ? "default" : "outline"} className='rounded-full'>Top Gainers</Button>

                        <Button onClick={() => handleCategory("topLosers")} 
                        variant={category == "topLosers" ? "default" : "outline"} className='rounded-full'>Top Losers</Button>
                    </div>
                    <AssetTable/>
                </div>

                <div className='hidden lg:block lg:w-[50%] p-5'>
                    <StockChart/>
                </div>
                <div className='flex gap-5 items-center'>
                    <div>
                        <Avatar>
                            <AvatarImage src={'https://bin.bnbstatic.com/image/admin_mgs_image_upload/20220218/94863af2-c980-42cf-a139-7b9f462a36c2.png'}/>
                        </Avatar>
                    </div>
                    <div className='flex items-center gap-2'>
                        <p>ETH</p>
                        <DotIcon className='text-gray-400'/>
                        <p className='text-gray-400'>Ethereum</p>
                    </div>
                    <div className='flex items-end gap-2'>
                        <p className='text-xl font-bold'>2935</p>
                        <p className='text-red-600'>-145236347347.90</p>
                        <span>(-1.23532637%)</span>
                    </div>
                </div>
            </div>
        </div>
    )
}
